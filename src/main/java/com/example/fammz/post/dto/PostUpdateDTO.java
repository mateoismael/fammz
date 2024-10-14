<<<<<<< HEAD:src/main/java/com/example/fammz/post/dto/PostUpdateDTO.java
package com.example.fammz.post.DTOs;
=======
package com.example.fammz.Post.dto;
>>>>>>> 618f301e2124279a1822e480b17dc282eb10be18:src/main/java/com/example/fammz/Post/dto/PostUpdateDTO.java

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
