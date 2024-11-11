package com.ml.gs_relation.service;

import com.ml.gs_relation.Dto.JwtAuthenticationResponse;
import com.ml.gs_relation.Dto.RefreshTokenRequest;
import com.ml.gs_relation.Dto.SignupRequest;
import com.ml.gs_relation.entite.User;

public interface AuthenticationService {
    User signup(SignupRequest signupRequest);
    User signupAgriculteur(SignupRequest signupRequest);
    JwtAuthenticationResponse signin(SignupRequest signupRequest);
    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
