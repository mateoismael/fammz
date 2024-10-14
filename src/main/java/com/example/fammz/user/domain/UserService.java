package com.example.fammz.user.domain;

import com.example.fammz.exception.ResourceNotFoundException;
import com.example.fammz.user.dto.UserCreateDto;
import com.example.fammz.user.dto.UserResponseDto;
import com.example.fammz.user.infrastructure.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponseDto createUser(UserCreateDto userCreateDto) {
        // La validación de @StrongPassword se realizará automáticamente gracias a @Valid en el controlador
        User user = new User();
        user.setName(userCreateDto.getName());
        user.setEmail(userCreateDto.getEmail());
        user.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));
        User savedUser = userRepository.save(user);
        return convertToResponseDto(savedUser);
    }

    public UserResponseDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return convertToResponseDto(user);
    }

    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateUser(Long id, UserCreateDto userCreateDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.setName(userCreateDto.getName());
        user.setEmail(userCreateDto.getEmail());
        // Solo actualiza la contraseña si se proporciona una nueva
        if (userCreateDto.getPassword() != null && !userCreateDto.getPassword().isEmpty()) {
            // La validación de @StrongPassword se realizará automáticamente
            user.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));
        }
        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private UserResponseDto convertToResponseDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setName(user.getName());
        userResponseDto.setEmail(user.getEmail());
        // No incluimos la contraseña en el DTO por razones de seguridad
        return userResponseDto;
    }
}