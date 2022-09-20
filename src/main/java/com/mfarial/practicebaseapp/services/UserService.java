package com.mfarial.practicebaseapp.services;

import com.mfarial.practicebaseapp.dto.request.RegisterRequest;
import com.mfarial.practicebaseapp.dto.response.BaseResponse;
import com.mfarial.practicebaseapp.entities.EmailVerification;
import com.mfarial.practicebaseapp.entities.Role;
import com.mfarial.practicebaseapp.entities.User;
import com.mfarial.practicebaseapp.enums.EnumRole;
import com.mfarial.practicebaseapp.repositories.RoleRepository;
import com.mfarial.practicebaseapp.repositories.UserRepository;
import com.mfarial.practicebaseapp.utils.GeneralUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserService {
    @Autowired
    UserRepository userRepository;

    public int enableUser(String email) {
        return userRepository.enableUser(email);
    }
}
