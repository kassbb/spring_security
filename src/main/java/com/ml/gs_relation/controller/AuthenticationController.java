package com.ml.gs_relation.controller;

import com.ml.gs_relation.Dto.SignupRequest;
import com.ml.gs_relation.entite.User;
import com.ml.gs_relation.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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


}
