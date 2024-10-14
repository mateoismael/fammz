package com.example.fammz.comment.dto;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class CommentCreateDto {
    @NotNull
    @Size(min = 1, max = 500)
    private String content;

    @NotNull
    private Long userId;

    @NotNull
    private Long postId;
}