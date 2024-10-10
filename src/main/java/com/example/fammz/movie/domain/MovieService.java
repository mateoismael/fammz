package com.example.fammz.movie.domain;

import com.example.fammz.movie.infrastructure.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    // Obtener todas las películas con filtro opcional por género y año
    public List<Movie> getAllMovies(String genre, Integer releaseYear) {
        if (genre != null && releaseYear != null) {
            return movieRepository.findByGenreAndReleaseYear(genre, releaseYear);
        } else if (genre != null) {
            return movieRepository.findByGenre(genre);
        } else if (releaseYear != null) {
            return movieRepository.findByReleaseYear(releaseYear);
        } else {
            return movieRepository.findAll();
        }
    }

    // Obtener una película por su ID
    public Movie getMovieById(Long id) {
        Optional<Movie> movie = movieRepository.findById(id);
        return movie.orElse(null);
    }

    // Crear una nueva película
    public Movie createMovie(Movie movie, List<Long> actorIds) {
        // Aquí podrías agregar lógica para asociar actores a la película si es necesario
        return movieRepository.save(movie);
    }

    // Actualizar una película existente
    public Movie updateMovie(Long id, Movie movieDetails) {
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        if (optionalMovie.isPresent()) {
            Movie movie = optionalMovie.get();
            movie.setTitle(movieDetails.getTitle());
            movie.setReleaseYear(movieDetails.getReleaseYear());
            movie.setGenre(movieDetails.getGenre());
            movie.setDirector(movieDetails.getDirector());
            movie.setSynopsis(movieDetails.getSynopsis());
            movie.setPosterUrl(movieDetails.getPosterUrl());
            return movieRepository.save(movie);
        } else {
            return null;
        }
    }

    // Eliminar una película
    public boolean deleteMovie(Long id) {
        if (movieRepository.existsById(id)) {
            movieRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
