package com.dmtryii.task.service.impl;

import com.dmtryii.task.dto.director.DirectorInfoDto;
import com.dmtryii.task.exception.ResourceNotFoundException;
import com.dmtryii.task.model.Director;
import com.dmtryii.task.model.Film;
import com.dmtryii.task.repository.DirectorRepository;
import com.dmtryii.task.repository.FilmRepository;
import com.dmtryii.task.service.FilmService;
import com.dmtryii.task.dto.film.FilmDetailsDto;
import com.dmtryii.task.dto.film.FilmInfoDto;
import com.dmtryii.task.dto.film.FilmSearchDto;
import com.dmtryii.task.dto.film.FilmSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;

    private final DirectorRepository directorRepository;

    @Override
    public Long save(FilmSaveDto dto) {
        validateDto(dto);
        Film film = new Film();
        updateFilmFromDto(film, dto);
        return filmRepository.save(film).getId();
    }

    @Override
    public FilmDetailsDto findById(Long id) {
        Film film = getOrThrow(id);
        return toFilmDetails(film);
    }

    @Override
    public List<FilmDetailsDto> findAll() {
        return filmRepository.findAll().stream()
                .map(this::toFilmDetails)
                .toList();
    }

    @Override
    public void update(Long id, FilmSaveDto dto) {
        validateDto(dto);
        Film film = getOrThrow(id);
        updateFilmFromDto(film, dto);
        filmRepository.save(film);
    }

    @Override
    public void delete(Long id) {
        Film film = getOrThrow(id);
        filmRepository.deleteById(film.getId());
    }

    @Override
    public List<FilmDetailsDto> searchFilm(FilmSearchDto dto) {
        Director director = resolveDirector(dto.getDirectorId());
        Integer year = dto.getYear();
        Pageable pageable = dto.getPage() == null || dto.getSize() == null
                ? Pageable.unpaged()
                : PageRequest.of(dto.getPage() - 1, dto.getSize());

        Page<Film> films = filmRepository.searchAllByDirectorOrYear(director, year, pageable);

        return films.stream()
                .map(this::toFilmDetails)
                .toList();
    }

    private void validateDto(FilmSaveDto dto) {
        filmRepository.findByRating(dto.getRating()).ifPresent(book -> {
            throw new IllegalArgumentException("film with given rating already exists");
        });

        if (dto.getReleaseDate() != null && dto.getReleaseDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("releaseDate should be before now");
        }
    }

    private void updateFilmFromDto(Film film, FilmSaveDto dto) {
        film.setTitle(dto.getTitle());
        film.setRating(dto.getRating());
        film.setReleaseDate(dto.getReleaseDate());
        film.setDirector(resolveDirector(dto.getDirectorId()));
    }

    private Director resolveDirector(Long directorId) {
        if (directorId == null) {
            return null;
        }
        return directorRepository.findById(directorId).
                orElseThrow(() -> new IllegalArgumentException("Director with id %d not found".formatted(directorId)));
    }

    private Film getOrThrow(Long bookId) {
        return filmRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Film with id %d not found".formatted(bookId)));
    }

    private FilmDetailsDto toFilmDetails(Film film) {
        return FilmDetailsDto.builder()
                .filmInfo(new FilmInfoDto(film.getId(), film.getTitle(), film.getReleaseDate()))
                .rating(film.getRating())
                .director(toDirectorInfo(film.getDirector()))
                .build();
    }

    private DirectorInfoDto toDirectorInfo(Director director) {
        if (director == null) {
            return null;
        }
        return new DirectorInfoDto(director.getId(), director.getFirstName(), director.getLastName());
    }
}
