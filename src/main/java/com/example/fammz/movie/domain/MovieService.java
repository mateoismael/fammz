package com.example.fammz.movie.domain;

import com.example.fammz.movie.dto.MovieCreateDto;
import com.example.fammz.movie.dto.MovieResponseDto;
import com.example.fammz.movie.infrastructure.MovieRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Transactional
    public MovieResponseDto createMovie(MovieCreateDto movieCreateDto) {
        Movie movie = new Movie();
        updateMovieFromDto(movie, movieCreateDto);
        Movie savedMovie = movieRepository.save(movie);
        return convertToResponseDto(savedMovie);
    }

    public MovieResponseDto getMovieById(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
        return convertToResponseDto(movie);
    }

    public List<MovieResponseDto> getAllMovies() {
        return movieRepository.findAll().stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public MovieResponseDto updateMovie(Long id, MovieCreateDto movieCreateDto) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
        updateMovieFromDto(movie, movieCreateDto);
        Movie updatedMovie = movieRepository.save(movie);
        return convertToResponseDto(updatedMovie);
    }

    @Transactional
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

    private void updateMovieFromDto(Movie movie, MovieCreateDto dto) {
        movie.setTitle(dto.getTitle());
        movie.setReleaseYear(dto.getReleaseYear());
        movie.setDirector(dto.getDirector());
    }

    private MovieResponseDto convertToResponseDto(Movie movie) {
        MovieResponseDto dto = new MovieResponseDto();
        dto.setId(movie.getId());
        dto.setTitle(movie.getTitle());
        dto.setReleaseYear(movie.getReleaseYear());
        dto.setDirector(movie.getDirector());
        return dto;
    }
}