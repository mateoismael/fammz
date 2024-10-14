<<<<<<< HEAD:src/main/java/com/example/fammz/post/dto/PostDTO.java
package com.example.fammz.post.DTOs;
=======
package com.example.fammz.Post.dto;
>>>>>>> 618f301e2124279a1822e480b17dc282eb10be18:src/main/java/com/example/fammz/Post/dto/PostDTO.java

public class PostDTO {

    private Long id;
    private Long userId;
    private Long movieId;
    private String content;
    private Integer likesCount;
    private Integer commentsCount;

    // Constructor vacío
    public PostDTO() {}

    // Constructor con parámetros
    public PostDTO(Long id, Long userId, Long movieId, String content, Integer likesCount, Integer commentsCount) {
        this.id = id;
        this.userId = userId;
        this.movieId = movieId;
        this.content = content;
        this.likesCount = likesCount;
        this.commentsCount = commentsCount;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Integer getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(Integer likesCount) {
        this.likesCount = likesCount;
    }

    public Integer getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(Integer commentsCount) {
        this.commentsCount = commentsCount;
    }
}
