package pl.sdacademy;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * http://dominisz.pl
 * 16.11.2017
 */
@Data
@AllArgsConstructor
public class Track {

    private String author;
    private int length;
    private String title;
    private String notes;

}
