package com.dmtryii.task.service.impl;

import com.dmtryii.task.dto.director.DirectorDetailsDto;
import com.dmtryii.task.dto.director.DirectorInfoDto;
import com.dmtryii.task.model.Director;
import com.dmtryii.task.model.Film;
import com.dmtryii.task.repository.DirectorRepository;
import com.dmtryii.task.service.DirectorService;
import com.dmtryii.task.dto.film.FilmInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DirectorServiceImpl implements DirectorService {

    private final DirectorRepository directorRepository;

    @Override
    @Transactional
    public List<DirectorDetailsDto> findAll() {
        return directorRepository.findAll().stream()
                .map(this::toDirectorDetails)
                .toList();
    }

    private DirectorDetailsDto toDirectorDetails(Director director) {
        return DirectorDetailsDto.builder()
                .director(new DirectorInfoDto(director.getId(),
                                              director.getFirstName(),
                                              director.getLastName()))
                .films(toFilmInfo(director.getFilms()))
                .build();
    }

    private List<FilmInfoDto> toFilmInfo(Set<Film> films) {
        return films.stream()
                .map(book -> new FilmInfoDto(book.getId(),
                                             book.getTitle(),
                                             book.getReleaseDate()))
                .toList();
    }
}
