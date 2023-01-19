package com.dmtryii.task.repository;

import com.dmtryii.task.model.Director;
import com.dmtryii.task.model.Film;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {

    Optional<Film> findByRating(Double rating);

    @Query( "SELECT b " +
            "FROM Film b " +
            "WHERE b.director = :author OR YEAR(b.releaseDate) = :year OR b.rating >= :rating")
    Page<Film> searchAllByDirectorOrYear(@Param("author") Director author,
                                         @Param("year") Integer year,
                                         @Param("rating") Double rating, Pageable pageable);
}
