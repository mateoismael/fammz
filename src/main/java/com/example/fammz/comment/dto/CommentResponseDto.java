package com.example.fammz.comment.dto;

import lombok.Data;
import java.time.ZonedDateTime;

@Data
public class CommentResponseDto {
    private Long id;
    private String content;
    private Long userId;
    private String userName;
    private Long postId;
    private ZonedDateTime createdAt;
}