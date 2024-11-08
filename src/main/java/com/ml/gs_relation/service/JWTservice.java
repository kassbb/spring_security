package com.ml.gs_relation.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JWTservice {
    public String extractUsername(String token);
    public String generateToken(UserDetails userDetails);
    public boolean isTokenValid(String token, UserDetails userDetails);

}
