package com.example.fammz.movie.infrastructure;

import com.example.fammz.movie.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    // Métodos personalizados si son necesarios
}