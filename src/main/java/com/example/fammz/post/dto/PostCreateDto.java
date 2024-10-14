package com.example.fammz.post.dto;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class PostCreateDto {
    @NotNull
    @Size(min = 1, max = 1000)
    private String content;

    @NotNull
    private Long userId;

    @NotNull
    private Long movieId;
}
