package com.dmtryii.task.dto.film;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDate;

@Getter
@Builder
@Jacksonized
@AllArgsConstructor
public class FilmInfoDto {

    private Long id;
    private String title;
    private LocalDate releaseDate;
}
