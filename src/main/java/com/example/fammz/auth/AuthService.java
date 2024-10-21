package com.example.fammz.auth;

import com.example.fammz.security.JwtTokenProvider;
import com.example.fammz.user.domain.User;
import com.example.fammz.user.domain.Role;
import com.example.fammz.user.infrastructure.UserRepository;
import com.example.fammz.email.UserRegistrationEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public JwtAuthenticationResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return new JwtAuthenticationResponse(jwt);
    }

    public User registerUser(SignUpRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new RuntimeException("Email Address already in use!");
        }

        User user = new User();
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setRoles(Collections.singleton(Role.USER));

        User savedUser = userRepository.save(user);

        // Preparar los datos para el correo de bienvenida
        Map<String, Object> templateModel = new HashMap<>();
        templateModel.put("userName", savedUser.getName());
        templateModel.put("userEmail", savedUser.getEmail());

        // Publicar el evento para enviar el correo de bienvenida
        eventPublisher.publishEvent(new UserRegistrationEvent(savedUser.getEmail(), "Bienvenido a FAMMZ", "welcome-email-template", templateModel));

        return savedUser;
    }
}