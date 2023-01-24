package com.dmtryii.task;

import com.dmtryii.task.dto.RestResponse;
import com.dmtryii.task.dto.film.FilmSaveDto;
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

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



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

    private Director createDirector(String firstName, String lastName) {
        Director director = new Director();
        director.setFirstName(firstName);
        director.setLastName(lastName);
        return directorRepository.save(director);
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
