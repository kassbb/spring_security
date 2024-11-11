package com.ml.gs_relation.service.impl;

import com.ml.gs_relation.Dto.JwtAuthenticationResponse;
import com.ml.gs_relation.Dto.RefreshTokenRequest;
import com.ml.gs_relation.Dto.SignupRequest;
import com.ml.gs_relation.entite.Role;
import com.ml.gs_relation.entite.User;
import com.ml.gs_relation.repository.UserRepository;
import com.ml.gs_relation.service.AuthenticationService;
import com.ml.gs_relation.service.JWTservice;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceimpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTservice jwTservice;

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

    public JwtAuthenticationResponse signin(SignupRequest signupRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signupRequest.getEmail(), signupRequest.getPassword()));
        var user = userRepository.findByEmail(signupRequest.getEmail()).orElseThrow(() -> new IllegalArgumentException("email ou mot de passe invalide"));
        var jwt = jwTservice.generateToken(user);
        var refreshToken = jwTservice.generateRefreshToken(new HashMap<>(),user);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        return jwtAuthenticationResponse;
    }
    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest){
        String userEmail = jwTservice.extractUsername(refreshTokenRequest.getToken());
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        if (jwTservice.isTokenValid(refreshTokenRequest.getToken(),user)){
            var jwt= jwTservice.generateToken(user);

            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
            return jwtAuthenticationResponse;

        }
        return null;
    }


}
