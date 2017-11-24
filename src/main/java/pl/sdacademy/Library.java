package pl.sdacademy;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * http://dominisz.pl
 * 16.11.2017
 */
@Data
public class Library {

    private List<CD> CDs;

    public Library() {
        CDs = new ArrayList<>();
    }

    public List<CD> searchByGenre(Genre genre) {
        return CDs.stream()
                .filter(cd -> cd.getGenres().contains(genre))
                .collect(Collectors.toList());
    }

    public List<CD> searchByCDTitle(String title) {
        return CDs.stream()
                .filter(cd -> cd.getTitle().contains(title))
                .collect(Collectors.toList());
    }

    public List<CD> searchByTrackTitle(String title) {
        return CDs.stream()
                .filter(cd -> !cd.searchTracksByTitle(title).isEmpty())
                .collect(Collectors.toList());
    }

    public void addCD(CD cd) {
        CDs.add(cd);
    }

    //TODO zaimplementować oraz dodać testy jednostkowe
    public void deleteCD(int index) {
        CDs.remove(index);
    }
}
