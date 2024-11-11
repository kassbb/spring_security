package com.ml.gs_relation.controller;

import com.ml.gs_relation.Dto.JwtAuthenticationResponse;
import com.ml.gs_relation.Dto.RefreshTokenRequest;
import com.ml.gs_relation.Dto.SignupRequest;
import com.ml.gs_relation.entite.User;
import com.ml.gs_relation.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

   @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody SignupRequest signupRequest) {
        return ResponseEntity.ok(authenticationService.signup(signupRequest));

    }
    @PostMapping("signup/agricuture")
    public  ResponseEntity<User> signupAgriculteur(@RequestBody SignupRequest signupRequest){
       return  ResponseEntity.ok(authenticationService.signupAgriculteur(signupRequest));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SignupRequest signupRequest){
        try {
            JwtAuthenticationResponse response = authenticationService.signin(signupRequest);
            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();  // 401 Unauthorized
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();  // 400 Bad Request
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest){
        try {
            JwtAuthenticationResponse response = authenticationService.refreshToken(refreshTokenRequest);
            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();  // 401 Unauthorized
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();  // 400 Bad Request
        }
    }


}
