package pl.sdacademy;

/**
 * http://dominisz.pl
 * 16.11.2017
 */
//TODO zamiast enuma Genre wykorzystać gatunki zdefiniowane w pliku tekstowym, dodać opcje dodawania nowego gatunku, dodać odczyt i zapis do pliku
public enum Genre {

    AFRICAN_HEAVY_METAL("African heavy metal"),
    EXPRERIMENTAL_MUSIC("Experimental music"),
    ROCK("Rock"),
    POP("Pop"),
    CLASSIC("Classic"),
    DRUM_AND_BASS("Drum'n'bass");

    private String name;

    Genre(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }

}
