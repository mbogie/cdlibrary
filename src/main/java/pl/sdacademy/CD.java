package pl.sdacademy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * http://dominisz.pl
 * 16.11.2017
 */
@Data
public class CD {

    private String band;
    private String title;
    private String publisher;
    private LocalDate releaseDate;
    private Set<Genre> genres;
    private List<Track> tracks;

    public CD(String band, String title, String publisher, LocalDate releaseDate) {
        this.band = band;
        this.title = title;
        this.publisher = publisher;
        this.releaseDate = releaseDate;
        this.genres = new HashSet<>();
        this.tracks = new ArrayList<>();
    }

    public CD() {
        this.genres = new HashSet<>();
        this.tracks = new ArrayList<>();
    }

    public void addGenre(Genre genre) {
        genres.add(genre);
    }

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
                + lengthToString() + ", "
                + genresToString();
    }

    //ma zwracać gatunki w postaci pełnych nazw, np. African heavy metal, Experimental music
    private String genresToString() {
        return genres.stream()
                .map(genre -> genre.toString())
                .collect(Collectors.joining(", "));
    }

    //ma zwracać czas w postaci mm:ss
    private String lengthToString() {
        int minutes = getLength() / 60;
        int seconds = getLength() % 60;
        return String.format("%d:%02d", minutes, seconds);
    }
}
