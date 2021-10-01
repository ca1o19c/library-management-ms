package dev.academy.movieapi.repository;

import dev.academy.movieapi.mock.MovieMock;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@DisplayName("Tests for movie repository")
class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @Test
    @DisplayName("should create movie when successful")
    void shouldCreateOne() {
        var movieToBeSaved = MovieMock.aMock();
        var movieSaved = this.movieRepository.save(movieToBeSaved);

        assertThat(movieSaved).isNotNull();
        assertThat(movieSaved.getId()).isNotNull();
        assertThat(movieSaved.getName()).isEqualTo(movieToBeSaved.getName());
        assertThat(movieSaved.getCreatedOn()).isEqualTo(movieToBeSaved.getCreatedOn());
    }

    @Test
    @DisplayName("should find by name when successful")
    void shouldFindByName() {
        var movieToBeSaved = MovieMock.aMock();
        var movieSaved = this.movieRepository.save(movieToBeSaved);
        var name = movieSaved.getName();

        var movies = this.movieRepository.findByName(name);

        assertThat(movies)
                .isNotEmpty()
                .contains(movieSaved);
    }

    @Test
    @DisplayName("should return empty list when movie is not found")
    void shouldNotFindByName() {
        var movies = this.movieRepository.findByName("");

        assertThat(movies).isEmpty();
    }

    @Test
    @DisplayName("should update movie when successful")
    void shouldUpdateOne() {
        var movieToBeSaved = MovieMock.aMock();
        var movieSaved = this.movieRepository.save(movieToBeSaved);

        movieSaved.setName("Doctor Strange");
        movieSaved.setUpdatedOn(LocalDateTime.now());

        var movieUpdated = this.movieRepository.save(movieSaved);

        assertThat(movieUpdated).isNotNull();
        assertThat(movieUpdated.getId()).isNotNull();
        assertThat(movieUpdated.getName()).isEqualTo(movieSaved.getName());
        assertThat(movieUpdated.getCreatedOn()).isEqualTo(movieSaved.getCreatedOn());
        assertThat(movieUpdated.getUpdatedOn()).isEqualTo(movieSaved.getUpdatedOn());
    }

    @Test
    @DisplayName("should delete movie when successful")
    void shouldDeleteOne() {
        var movieToBeSaved = MovieMock.aMock();
        var movieSaved = this.movieRepository.save(movieToBeSaved);

        this.movieRepository.delete(movieSaved);

        var movieId = this.movieRepository.findById(movieSaved.getId());

        assertThat(movieId).isEmpty();
    }
}