package com.ml.gs_relation.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;
@Service
public interface JWTservice {
    public String extractUsername(String token);
    public String generateToken(UserDetails userDetails);
    public boolean isTokenValid(String token, UserDetails userDetails);

     String generateRefreshToken(Map<String, Objects> extraClain, UserDetails userDetails);
}
