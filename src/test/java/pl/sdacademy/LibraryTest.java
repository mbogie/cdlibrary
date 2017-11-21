package pl.sdacademy;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * http://dominisz.pl
 * 20.11.2017
 */
class LibraryTest {

    //Wyszukanie gatunku na nowo utworzonej bibliotece (bez płyt CD) nie powinno znaleźć żadnej płyty
    @Test
    void searchByGenreOnEmptyCDShouldReturnEmptyList() {
        Library library = new Library();
        List<CD> result = library.searchByGenre(Genre.ROCK);
        assertTrue(result.isEmpty());
    }

    //Wyszukanie gatunku na bibliotece z płytami CD nie powinno nic znaleźć, jeżeli nie istnieje szukany gatunek
    @Test
    void searchByGenreWithNonExistingGenreShouldReturnEmptyList() {
        Library library = new Library();
        CD cd = createCDWithGenres(Genre.ROCK, Genre.POP);
        List<CD> CDs = new ArrayList<>();
        CDs.add(cd);
        library.setCDs(CDs);
        List<CD> result = library.searchByGenre(Genre.AFRICAN_HEAVY_METAL);
        assertTrue(result.isEmpty());
    }

    //Wyszukanie gatunku powinno znaleźć płyty o podanym gatunku
    @Test
    void searchByGenreShouldReturnSomeResults() {
        Library library = new Library();
        CD cd1 = createCDWithGenres(Genre.ROCK);
        CD cd2 = createCDWithGenres(Genre.ROCK, Genre.POP);
        CD cd3 = createCDWithGenres(Genre.CLASSIC);
        List<CD> CDs = new ArrayList<>();
        CDs.add(cd1);
        CDs.add(cd2);
        CDs.add(cd3);
        library.setCDs(CDs);
        List<CD> result = library.searchByGenre(Genre.ROCK);
        assertTrue(result.size() == 2);
        assertTrue(result.get(0).getGenres().contains(Genre.ROCK));
        assertTrue(result.get(1).getGenres().contains(Genre.ROCK));
    }

    private CD createCDWithGenres(Genre ... genres) {
        CD cd = new CD();
        Set<Genre> setOfGenres = new HashSet<>();
        for (Genre genre : genres) {
            setOfGenres.add(genre);
        }
        cd.setGenres(setOfGenres);
        return cd;
    }
}
