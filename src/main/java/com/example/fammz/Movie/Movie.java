package com.example.fammz.Movie;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.example.fammz.Cast.Cast;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private Integer releaseYear;
    private String genre;
    private String director;
    private String synopsis;
    private String posterUrl;

    @JsonManagedReference
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Cast> castList;  // Relación con Cast
}
