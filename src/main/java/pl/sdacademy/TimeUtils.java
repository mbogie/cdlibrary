package pl.sdacademy;

/**
 * http://dominisz.pl
 * 23.11.2017
 */
public class TimeUtils {

    public static String formattedLength(int length) {
        int minutes = length / 60;
        int seconds = length % 60;
        return String.format("%d:%02d", minutes, seconds);
    }
}
