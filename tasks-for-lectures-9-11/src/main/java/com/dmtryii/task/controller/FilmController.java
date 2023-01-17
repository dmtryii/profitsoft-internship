package com.dmtryii.task.controller;

import com.dmtryii.task.dto.RestResponse;
import com.dmtryii.task.dto.film.FilmDetailsDto;
import com.dmtryii.task.dto.film.FilmSearchDto;
import com.dmtryii.task.dto.film.FilmSaveDto;
import com.dmtryii.task.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/films")
@RequiredArgsConstructor
public class FilmController {

    private final FilmService filmService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestResponse createFilm(@Valid @RequestBody FilmSaveDto dto) {
        Long id = filmService.save(dto);
        return new RestResponse(String.valueOf(id));
    }

    @GetMapping("/{id}")
    public FilmDetailsDto getFilm(@PathVariable Long id) {
        return filmService.findById(id);
    }

    @GetMapping
    public ResponseEntity<List<FilmDetailsDto>> getAllFilms() {
        List<FilmDetailsDto> films = filmService.findAll();
        return films.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(films);
    }

    @PutMapping("/{id}")
    public RestResponse updateFilm(@PathVariable Long id, @Valid @RequestBody FilmSaveDto dto) {
        filmService.update(id, dto);
        return new RestResponse("Update was successful");
    }

    @PostMapping("/_search")
    public ResponseEntity<List<FilmDetailsDto>> searchFilm(@Valid @RequestBody FilmSearchDto dto) {
        List<FilmDetailsDto> films = filmService.searchFilm(dto);
        return films.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(films);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public RestResponse deleteFilm(@PathVariable Long id) {
        filmService.delete(id);
        return new RestResponse("Delete was successful");
    }

}
