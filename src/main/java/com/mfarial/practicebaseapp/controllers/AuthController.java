package com.mfarial.practicebaseapp.controllers;

import com.mfarial.practicebaseapp.dto.request.LoginRequest;
import com.mfarial.practicebaseapp.dto.request.RegisterRequest;
import com.mfarial.practicebaseapp.dto.response.BaseResponse;
import com.mfarial.practicebaseapp.dto.response.LoginResponse;
import com.mfarial.practicebaseapp.repositories.UserRepository;
import com.mfarial.practicebaseapp.services.MailService;
import com.mfarial.practicebaseapp.services.RegistrationService;
import com.mfarial.practicebaseapp.services.UserDetailsImpl;
import com.mfarial.practicebaseapp.utils.JwtUtils;
import lombok.AllArgsConstructor;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    UserRepository userRepository;

    @Autowired
    RegistrationService registrationService;

    @Autowired
    MailService mailSender;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        UserDetailsImpl userDetails = ( UserDetailsImpl) authentication.getPrincipal();
        if (!userDetails.isEnabled())
            return ResponseEntity.badRequest().body(new BaseResponse("Please activate your account."));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new LoginResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser( @RequestBody RegisterRequest registerRequest) throws EmailException {
        if (userRepository.existsByUsernameAndEnabledTrue(registerRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new BaseResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmailAndEnabledTrue(registerRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new BaseResponse("Error: Email is already in use!"));
        }

        String token = registrationService.register(registerRequest);
        mailSender.sendEmail(token, registerRequest.getEmail());

        return ResponseEntity.ok(new BaseResponse("User registered successfully!"));
    }

    @GetMapping(path = "/verify")
    public void confirm(@RequestParam("token") String token, HttpServletResponse response) throws IOException {
        registrationService.verifyUser(token);
        response.sendRedirect("http://localhost:3000");
    }
}
