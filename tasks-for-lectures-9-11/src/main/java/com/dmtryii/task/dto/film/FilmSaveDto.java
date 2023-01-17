package com.dmtryii.task.dto.film;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Getter
@Builder
@Jacksonized
public class FilmSaveDto {

    @NotBlank(message = "title is required")
    private String title;
    @Positive(message = "rating must be positive number")
    private Double rating;
    private LocalDate releaseDate;
    @Positive(message = "directorId must be positive number")
    private Long directorId;
}
