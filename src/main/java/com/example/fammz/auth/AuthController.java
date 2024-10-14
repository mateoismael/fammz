package com.example.fammz.auth;

import com.example.fammz.auth.LoginRequest;
import com.example.fammz.auth.SignUpRequest;
import com.example.fammz.auth.JwtAuthenticationResponse;
import com.example.fammz.auth.AuthService;
import com.example.fammz.user.domain.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        JwtAuthenticationResponse jwt = authService.authenticateUser(loginRequest);
        return ResponseEntity.ok(jwt);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        User user = authService.registerUser(signUpRequest);
        return ResponseEntity.ok("User registered successfully");
    }
}