package com.dmtryii.task.service;

import com.dmtryii.task.dto.film.FilmDetailsDto;
import com.dmtryii.task.dto.film.FilmSearchDto;
import com.dmtryii.task.dto.film.FilmSaveDto;

import java.util.List;

public interface FilmService {

    Long save(FilmSaveDto dto);

    FilmDetailsDto findById(Long id);

    List<FilmDetailsDto> findAll();

    void update(Long id, FilmSaveDto dto);

    void delete(Long id);

    List<FilmDetailsDto> searchFilm(FilmSearchDto dto);
}
