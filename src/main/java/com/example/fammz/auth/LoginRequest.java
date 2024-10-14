package com.example.fammz.auth;


import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class LoginRequest {
    @NotNull
    @Email
    private String email;

    @NotBlank
    private String password;

}