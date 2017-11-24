package pl.sdacademy;

import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static pl.sdacademy.TimeUtils.formattedLength;

/**
 * Klasa CD przechowuje informacje o jednej płycie, m.in. nazwę wykonawcy, tytuł, utwory, gatunki...
 *
 * @author http://dominisz.pl
 * @version 16.11.2017
 */
@Data
public class CD {

    /**
     * Nazwa wykonawcy
     */
    private String band;
    /**
     * Tytuł płyty
     */
    private String title;
    /**
     * Wydawca płyty
     */
    private String publisher;
    /**
     * Data wydania płyty
     */
    private LocalDate releaseDate;
    /**
     * Gatunki muzyczne
     */
    private Set<Genre> genres;
    /**
     * Lista utworów
     */
    private List<Track> tracks;

    public CD(String band, String title, String publisher, LocalDate releaseDate) {
        this.band = band;
        this.title = title;
        this.publisher = publisher;
        this.releaseDate = releaseDate;
        this.genres = new HashSet<>();
        this.tracks = new ArrayList<>();
    }

    /**
     * Konstruktor tworzy nowy obiekt CD z pustą listą genres oraz pustą listą tracks
     */
    public CD() {
        this.genres = new HashSet<>();
        this.tracks = new ArrayList<>();
    }

    /**
     * Metoda dodaje do płyty gatunek
     *
     * @param genre gatunek dodawany do płyty
     */
    public void addGenre(Genre genre) {
        genres.add(genre);
    }

    /**
     * Usuwa podany gatunek z płyty
     *
     * @param genre gatunek do usunięcia
     * @see Genre
     */
    public void deleteGenre(Genre genre) {
        genres.remove(genre);
    }

    public void addTrack(Track track) {
        tracks.add(track);
    }

    public void deleteTrack(int trackNumber) {
        tracks.remove(trackNumber);
    }

    public int getLength() {
        int sum = 0;
        for (Track track : tracks) {
            sum += track.getLength();
        }
        return sum;
        //tracks.stream().mapToInt(track -> track.getLength()).sum();
    }

    public int getTrackCount() {
        return tracks.size();
    }

    public List<Track> searchTracksByTitle(String title) {
        return tracks.stream()
                .filter(track -> track.getTitle().contains(title))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return band + ", "
                + title + ", "
                + releaseDate + ", "
                + formattedLength(getLength()) + ", "
                + genresToString() + "\n"
                + tracksToString();
    }

    private String tracksToString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < tracks.size(); i++) {
            stringBuilder.append("\t")
                    .append(i + 1)
                    .append(". ")
                    .append(tracks.get(i));
            if (i < tracks.size() - 1) {
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }

    //ma zwracać gatunki w postaci pełnych nazw, np. African heavy metal, Experimental music
    private String genresToString() {
        return genres.stream()
                .map(Genre::toString)
                .collect(Collectors.joining(", "));
    }

}
