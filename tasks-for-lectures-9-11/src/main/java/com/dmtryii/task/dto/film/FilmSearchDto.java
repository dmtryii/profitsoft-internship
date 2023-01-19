package com.dmtryii.task.dto.film;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
@AllArgsConstructor
public class FilmSearchDto {

    private Long directorId;
    @Min(value = 1895, message = "the year cannot be less than 1895")
    @Max(value = 2025, message = "the year cannot be greater than 2025")
    private Integer year;
    @Min(value = 0, message = "rating cannot be less than 0")
    @Max(value = 1, message = "rating cannot be less than 1")
    private Double rating;
    @Min(value = 1, message = "page number should be positive number")
    private Integer page;
    @Min(value = 1, message = "page size should be positive number")
    private Integer size;
}
