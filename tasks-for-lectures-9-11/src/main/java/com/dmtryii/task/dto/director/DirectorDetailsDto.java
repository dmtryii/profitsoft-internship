package com.dmtryii.task.dto.director;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.dmtryii.task.dto.film.FilmInfoDto;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Getter
@Jacksonized
@Builder
public class DirectorDetailsDto {

    @JsonUnwrapped
    private DirectorInfoDto director;
    private List<FilmInfoDto> films;
}
