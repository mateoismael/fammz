package com.example.fammz.user.dto;

import lombok.Data;

@Data
public class UserResponseDto {
    private Long id;
    private String name;
    private String email;
    // No incluimos la contraseña por razones de seguridad
}