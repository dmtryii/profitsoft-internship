package com.dmtryii.task.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@ToString(of = {"title", "rating", "releaseDate", "director"})
@EqualsAndHashCode(of = {"id"})
@Table(name = "film")
@Entity
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "rating", nullable = false)
    private Double rating;
    @Column(name = "release_date")
    private LocalDate releaseDate;
    @ManyToOne
    private Director director;
}
