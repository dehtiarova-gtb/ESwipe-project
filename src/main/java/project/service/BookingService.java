package project.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class BookingService {
    private String data;
    public boolean isValidData(String data) {
        try {
            DateTimeFormatter dtf =
                    DateTimeFormatter.ofPattern("dd.MM.uuuu")
                            .withResolverStyle(java.time.format.ResolverStyle.STRICT);
            LocalDate localDate = LocalDate.parse(data, dtf);
            return true;

        }
        catch (DateTimeParseException ex) {
            return false;
        }
    }
}
