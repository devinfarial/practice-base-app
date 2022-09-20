package com.mfarial.practicebaseapp.services;

import com.mfarial.practicebaseapp.dto.request.RegisterRequest;
import com.mfarial.practicebaseapp.dto.request.VerifiyRequest;
import com.mfarial.practicebaseapp.entities.EmailVerification;
import com.mfarial.practicebaseapp.entities.Role;
import com.mfarial.practicebaseapp.entities.User;
import com.mfarial.practicebaseapp.enums.EnumRole;
import com.mfarial.practicebaseapp.repositories.EmailVerificationRepository;
import com.mfarial.practicebaseapp.repositories.RoleRepository;
import com.mfarial.practicebaseapp.repositories.UserRepository;
import com.mfarial.practicebaseapp.utils.GeneralUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class RegistrationService {

    @Autowired
    GeneralUtils generalUtils;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    private final EmailVerificationRepository emailVerificationRepository;

    public RegistrationService(EmailVerificationRepository emailVerificationRepository) {
        this.emailVerificationRepository = emailVerificationRepository;
    }

    public void saveToken(EmailVerification token) {
        emailVerificationRepository.save(token);
    }

    public int setConfirmedAt(String token) {
        return emailVerificationRepository.updateConfirmedAt(
                token, LocalDateTime.now());
    }

    public String register(RegisterRequest registerRequest){
        User user = new User(registerRequest.getUsername(),
                registerRequest.getEmail(),
                encoder.encode(registerRequest.getPassword()));

        Set<String> strRoles = registerRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role staffRole = roleRepository.findByName(EnumRole.ROLE_STAFF)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(staffRole);
        }
        user.setRoles(roles);
        userRepository.save(user);

        String token = generalUtils.generateToken();

        EmailVerification emailVerification = new EmailVerification(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );

        saveToken(emailVerification);

        return token;
    }

    public Optional<EmailVerification> getToken(String token) {
        return emailVerificationRepository.findByToken(token);
    }

    @Transactional
    public String verifyUser(String token) {
        EmailVerification emailVerification = getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (emailVerification.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = emailVerification.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        setConfirmedAt(token);
        userService.enableUser(emailVerification.getUser().getEmail());
        return "confirmed";
    }
}
