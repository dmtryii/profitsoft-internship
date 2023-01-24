package com.dmtryii.task;

import com.dmtryii.task.dto.RestResponse;
import com.dmtryii.task.dto.film.FilmSaveDto;
import com.dmtryii.task.dto.film.FilmSearchDto;
import com.dmtryii.task.model.Director;
import com.dmtryii.task.model.Film;
import com.dmtryii.task.repository.DirectorRepository;
import com.dmtryii.task.repository.FilmRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;

import static org.hamcrest.Matchers.hasSize;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = Task911Application.class)
@AutoConfigureMockMvc
@Sql(scripts = "classpath:clear-tables.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class FilmControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DirectorRepository directorRepository;
    @Autowired
    private FilmRepository filmRepository;

    @AfterEach
    public void beforeEach() {
        directorRepository.deleteAll();
        filmRepository.deleteAll();
    }

    @Test
    public void createFilmSuccess() throws Exception{
        Director director = createDirector("New FirstName", "New LastName");
        FilmSaveDto saveDto = getFilmSaveDto("New title", 0.99, LocalDate.now(), director.getId());

        MvcResult mvcResult = mvc.perform(
                post("/api/films")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(saveDto))
                ).andExpect(status().isCreated())
                 .andReturn();

        RestResponse response = parseResponse(mvcResult, RestResponse.class);
        Long filmId = Long.parseLong(response.getResult());
        Film film = filmRepository.findById(filmId).orElse(null);

        assertThat(film).isNotNull();
        assertThat(film.getTitle()).isEqualTo(saveDto.getTitle());
        assertThat(film.getRating()).isEqualTo(saveDto.getRating());
        assertThat(film.getReleaseDate()).isEqualTo(saveDto.getReleaseDate());
        assertThat(film.getDirector().getId()).isEqualTo(saveDto.getDirectorId());
    }

    @Test
    public void getFilmSuccess() throws Exception {
        Director director = createDirector("New FirstName", "New LastName");
        Film film = createFilm("New film", 0.9, director);

        ResultActions response = mvc.perform(
                get("/api/films/{id}",
                film.getId()
        ));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(film.getId()))
                .andExpect(jsonPath("$.title").value(film.getTitle()))
                .andExpect(jsonPath("$.rating").value(film.getRating()))
                .andExpect(jsonPath("$.releaseDate").value(film.getReleaseDate().toString()))
                .andExpect(jsonPath("$.director.id").value(film.getDirector().getId()))
                .andExpect(jsonPath("$.director.firstName").value(film.getDirector().getFirstName()))
                .andExpect(jsonPath("$.director.lastName").value(film.getDirector().getLastName()));
    }

    @Test
    public void getAllFilmsSuccess() throws Exception {
        Director firstDirector = createDirector("FirstName", "FirstSurname");
        Director secondDirector = createDirector("SecondName", "SecondSurname");
        Director thirdDirector = createDirector("ThirdName", "ThirdSurname");

        createFilm("title", 0.9, firstDirector);
        createFilm("SecondTitle", 0.5, secondDirector);
        createFilm("ThirdTitle", 0.2, thirdDirector);
        createFilm("FourthTitle", 0.2, secondDirector);

        ResultActions response = mvc.perform(
                get("/api/films/")
        );

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[*].director.id").exists())
                .andExpect(jsonPath("$[*].director.firstName").exists())
                .andExpect(jsonPath("$[*].director.lastName").exists());
    }

    @Test
    public void updateFilmSuccess() throws Exception{
        Director director = createDirector("New FirstName", "New LastName");
        Film film = createFilm("New film", 0.9, director);
        FilmSaveDto saveDto = getFilmSaveDto("Update title", 0.95, LocalDate.now(), director.getId());

        mvc.perform(
                put("/api/films/{id}", film.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(saveDto))
                ).andExpect(status().isOk());

        Film updateFilm = filmRepository.findById(film.getId()).orElse(null);
        assertThat(updateFilm).isNotNull();
        assertThat(updateFilm.getTitle()).isEqualTo(saveDto.getTitle());
        assertThat(updateFilm.getRating()).isEqualTo(saveDto.getRating());
        assertThat(updateFilm.getReleaseDate()).isEqualTo(saveDto.getReleaseDate());
        assertThat(updateFilm.getDirector().getId()).isEqualTo(saveDto.getDirectorId());
    }

    @Test
    public void deleteBookSuccess() throws Exception {
        Director director = createDirector("New FirstName", "New LastName");
        Film film = createFilm("New film", 0.9, director);

        mvc.perform(
              delete("/api/films/{id}", film.getId())
        ).andExpect(status().isNoContent());

        assertThat(filmRepository.findById(film.getId())).isEmpty();
    }

    private Director createDirector(String firstName, String lastName) {
        Director director = new Director();
        director.setFirstName(firstName);
        director.setLastName(lastName);
        return directorRepository.save(director);
    }

    private Film createFilm(String title, Double rating, Director director) {
        Film film = new Film();
        film.setTitle(title);
        film.setRating(rating);
        film.setReleaseDate(LocalDate.now());
        film.setDirector(director);
        return filmRepository.save(film);
    }

    private FilmSaveDto getFilmSaveDto(String title, Double rating, LocalDate releaseDate, Long directorId) {
        return FilmSaveDto.builder()
                .title(title)
                .rating(rating)
                .releaseDate(releaseDate)
                .directorId(directorId)
                .build();
    }

    private <T>T parseResponse(MvcResult mvcResult, Class<T> c) {
        try {
            return objectMapper.readValue(mvcResult.getResponse().getContentAsString(), c);
        } catch (JsonProcessingException | UnsupportedEncodingException e) {
            throw new RuntimeException("Error parsing JSON", e);
        }
    }
}
