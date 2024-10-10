package com.example.fammz.Post.DTOs;

public class PostUpdateDTO {

    private Long movieId;
    private String content;

    // Constructor vacío
    public PostUpdateDTO() {
    }

    // Constructor con parámetros
    public PostUpdateDTO(Long movieId, String content) {
        this.movieId = movieId;
        this.content = content;
    }

    // Getters y Setters
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
