package com.example.fammz.movie.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.fammz.post.domain.Post;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 1, max = 100)
    private String title;

    @NotNull
    @Min(1800)
    @Max(2100)
    private Integer releaseYear;

    @NotNull
    @Size(min = 1, max = 100)
    private String director;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts;
}
