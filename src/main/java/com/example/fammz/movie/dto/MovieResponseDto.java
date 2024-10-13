package com.example.fammz.movie.dto;

import lombok.Data;

@Data
public class MovieResponseDto {
    private Long id;
    private String title;
    private Integer releaseYear;
    private String director;
}