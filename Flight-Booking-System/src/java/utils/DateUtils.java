package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author manhphong
 */
public class DateUtils {

    public static LocalDateTime formatLocalDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedString = dateTime.format(formatter); 
        return LocalDateTime.parse(formattedString, formatter); 
    }

}
