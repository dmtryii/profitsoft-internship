package com.dmtryii.task;

import com.dmtryii.task.model.Director;
import com.dmtryii.task.model.Film;
import com.dmtryii.task.repository.DirectorRepository;
import com.dmtryii.task.repository.FilmRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
                classes = Task911Application.class)
@AutoConfigureMockMvc
public class DirectorControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private DirectorRepository directorRepository;

    @BeforeEach
    public void beforeEach() {
        filmRepository.deleteAll();
        directorRepository.deleteAll();
    }

    @Test
    public void getAllDirectorSuccess() throws Exception {
        Director firstDirector = createDirector("FirstName", "FirstSurname");
        Director secondDirector = createDirector("SecondName", "SecondSurname");
        Director thirdDirector = createDirector("ThirdName", "ThirdSurname");

        createFilm("title", 0.9, firstDirector);
        createFilm("SecondTitle", 0.5, secondDirector);
        createFilm("ThirdTitle", 0.2, thirdDirector);
        createFilm("FourthTitle", 0.2, secondDirector);

        ResultActions response = mvc.perform(MockMvcRequestBuilders.get("/api/directors"));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[*].films[*].id").exists())
                .andExpect(jsonPath("$[*].films[*].title").exists())
                .andExpect(jsonPath("$[*].films[*].releaseDate").exists());
    }

    @Test
    public void getAllDirectorNoContent() throws Exception {
        mvc.perform(get("/api/directors"))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$").doesNotExist());
    }

    private Director createDirector(String firstName, String lastName) {
        Director director = new Director();
        director.setFirstName(firstName);
        director.setLastName(lastName);
        return directorRepository.save(director);
    }

    private void createFilm(String title, Double rating, Director director) {
        Film film = new Film();
        film.setTitle(title);
        film.setRating(rating);
        film.setReleaseDate(LocalDate.now());
        film.setDirector(director);
        filmRepository.save(film);
    }
}
