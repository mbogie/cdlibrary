package pl.sdacademy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * http://dominisz.pl
 * 16.11.2017
 */
class CDTest {

    private String TITLE = "title";
    private String BAND = "band";
    private String PUBLISHER = "publisher";
    private LocalDate RELEASE_DATE = LocalDate.of(2017, 1, 1);
    private Genre GENRE = Genre.ROCK;

    private CD cd;

    @BeforeEach
    void setup() {
        cd = new CD(TITLE, BAND, PUBLISHER, RELEASE_DATE);
    }

    @Test
    void addingFirstGenreToNewCDShouldAddOneGenre() {
        assertTrue(cd.getGenres().isEmpty(), "New CD should have no genres");
        cd.addGenre(GENRE);
        assertTrue(cd.getGenres().size() == 1, "There should be only 1 genre");
        assertTrue(cd.getGenres().contains(GENRE), "Genres should be equal to " + GENRE.name());
    }

    @Test
    void deleteGenre() {
        cd.addGenre(GENRE);
        cd.deleteGenre(GENRE);
        assertTrue(cd.getGenres().isEmpty());
    }

    @Test
    void addTrack() {
    }

    @Test
    void deleteTrack() {
    }

    @Test
    void length() {
    }

    @Test
    void trackCount() {
    }

}