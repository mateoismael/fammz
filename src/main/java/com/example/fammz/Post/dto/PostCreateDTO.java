package com.example.fammz.Post.dto;

public class PostCreateDTO {

    private Long userId;
    private Long movieId;
    private String content;

    // Constructor
    public PostCreateDTO(Long userId, Long movieId, String content) {
        this.userId = userId;
        this.movieId = movieId;
        this.content = content;
    }

    // Getters y Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
