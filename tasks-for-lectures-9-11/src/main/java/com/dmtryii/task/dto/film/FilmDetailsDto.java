package com.dmtryii.task.dto.film;

import com.dmtryii.task.dto.director.DirectorInfoDto;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class FilmDetailsDto {

    @JsonUnwrapped
    private FilmInfoDto filmInfo;
    private Double rating;
    private DirectorInfoDto director;
}
