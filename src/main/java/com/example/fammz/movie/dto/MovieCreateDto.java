package com.example.fammz.movie.dto;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class MovieCreateDto {
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
}