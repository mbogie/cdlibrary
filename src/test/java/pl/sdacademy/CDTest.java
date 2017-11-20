package pl.sdacademy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * http://dominisz.pl
 * 16.11.2017
 */
class CDTest {

    private int NUMBER_OF_TRACKS = 5;
    private String TITLE = "title";
    private String TITLE2 = "title2";
    private String BAND = "band";
    private String PUBLISHER = "publisher";
    private LocalDate RELEASE_DATE = LocalDate.of(2017, 1, 1);
    private Genre GENRE = Genre.ROCK;
    private String AUTHOR = "author";
    private String AUTHOR2 = "author2";
    private int LENGTH = 45;
    private int LENGTH2 = 45;
    private String NOTES = "notes";
    private String NOTES2 = "notes2";
    private Track TRACK = new Track(AUTHOR, LENGTH, TITLE, NOTES);

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
        assertTrue(cd.getTracks().isEmpty());
        Track track = createTrack();
        cd.addTrack(track);
        assertTrue(cd.getTracks().size() == 1);
        assertEquals(cd.getTracks().get(0).getAuthor(), track.getAuthor());
        assertEquals(cd.getTracks().get(0).getLength(), track.getLength());
        assertEquals(cd.getTracks().get(0).getTitle(), track.getTitle());
        assertEquals(cd.getTracks().get(0).getNotes(), track.getNotes());
    }

    private List<Track> createTrackList(int numberOfTracks) {
        List<Track> trackList = new ArrayList<>();
        for (int i = 0; i < numberOfTracks; i++) {
            trackList.add(createTrack());
        }
        return trackList;
    }

    private Track createTrack() {
        Track track = new Track(AUTHOR, LENGTH, TITLE, NOTES);

        return track;
    }

    @Test
    void deleteTrackOnOneTrackCDShouldResultInEmptyCD() {
        List<Track> tracks = createTrackList(1);
        cd.setTracks(tracks);
        cd.deleteTrack(0);
        assertTrue(cd.getTracks().isEmpty());
    }

    @Test
    void deleteTrackOnTwoTrackCDShouldResultInOneTrackCD() {
        List<Track> tracks = new ArrayList<>();
        Track firstTrack = new TrackBuilder()
                .setAuthor(AUTHOR)
                .setLength(LENGTH)
                .setNotes(NOTES)
                .setTitle(TITLE)
                .build();
        tracks.add(firstTrack);
        Track secondTrack = new TrackBuilder()
                .setAuthor(AUTHOR2)
                .setLength(LENGTH2)
                .setTitle(TITLE2)
                .setNotes(NOTES2)
                .build();
        tracks.add(secondTrack);
        cd.setTracks(tracks);
        cd.deleteTrack(0);
        assertEquals(cd.getTracks().size(), 1);
        assertEquals(cd.getTracks().get(0).getAuthor(), AUTHOR2);
        assertEquals(cd.getTracks().get(0).getLength(), LENGTH2);
        assertEquals(cd.getTracks().get(0).getTitle(), TITLE2);
        assertEquals(cd.getTracks().get(0).getNotes(), NOTES2);
    }

    @Test
    void getLengthShouldReturnProperLength() {
        cd.setTracks(createTrackList(NUMBER_OF_TRACKS));
        assertTrue(cd.getLength() == NUMBER_OF_TRACKS * LENGTH);
    }

    @Test
    void getTrackCountShouldReturnProperNumberOfTracks() {
        cd.setTracks(createTrackList(NUMBER_OF_TRACKS));
        assertTrue(cd.getTrackCount() == NUMBER_OF_TRACKS);
    }

}