package pl.sdacademy;

import com.google.common.base.Strings;
import lombok.AllArgsConstructor;
import lombok.Data;

import static com.google.common.base.Strings.isNullOrEmpty;
import static pl.sdacademy.TimeUtils.formattedLength;

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

    @Override
    public String toString() {
        String result = author + ", " + formattedLength(length) + ", " + title;
        if (!isNullOrEmpty(notes)) {
            result += ", " + notes;
        }
        return result;
    }
}
