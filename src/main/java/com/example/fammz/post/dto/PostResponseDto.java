package com.example.fammz.post.dto;

import lombok.Data;
import java.time.ZonedDateTime;

@Data
public class PostResponseDto {
    private Long id;
    private String content;
    private Long userId;
    private String userName;
    private Long movieId;
    private String movieTitle;
    private ZonedDateTime createdAt;
}