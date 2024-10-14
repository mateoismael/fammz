package com.example.fammz.user.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserCreateDto {
    @NotNull
    @Size(min = 2, max = 50)
    private String name;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;
}