package com.example.fammz.Movie;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    // Obtener películas por género
    List<Movie> findByGenre(String genre);

    // Obtener películas por año de lanzamiento
    List<Movie> findByReleaseYear(Integer releaseYear);

    // Obtener películas por género y año de lanzamiento
    List<Movie> findByGenreAndReleaseYear(String genre, Integer releaseYear);
}
