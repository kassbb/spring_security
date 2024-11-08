package com.ml.gs_relation.service.impl;

import com.ml.gs_relation.Dto.SignupRequest;
import com.ml.gs_relation.entite.Role;
import com.ml.gs_relation.entite.User;
import com.ml.gs_relation.repository.UserRepository;
import com.ml.gs_relation.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceimpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User signup(SignupRequest signupRequest) {
        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.setUsername(signupRequest.getUsername());
        user.setRole(Role.ROLE_USER);
        return userRepository.save(user);
    }
    public User signupAgriculteur(SignupRequest signupRequest) {
        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.setUsername(signupRequest.getUsername());
        user.setRole(Role.ROLE_AGRICULTEUR);
        return userRepository.save(user);
    }

}
