package project.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class BookingService {
    public enum BookingResult {
        SUCCESS("Успешна заявка"),
        LOGIN_REQUIRED("Не съществуващи данни. Създайте профил"),
        DATE_REQUIRED("Не е въведена дата"),
        WRONG_DATE("Грешна дата"),
        ALREADY_BOOKED("Апартамент е зает"),
        WRONG_PASSWORD("Грешна парола");

        private final String message;

        BookingResult(String message) {
            this.message = message;
        }
        public String getMessage() {
            return message;
        }

    }
    private boolean logInStatus;
    public BookingResult clickBookApartment(long id, String date) {

        ApartmentService apartmentService = new ApartmentService();


        if(date == null || date.isBlank()) {
           return BookingResult.DATE_REQUIRED;
        }

        if(!(isValidDate(date))) {
            return BookingResult.WRONG_DATE;
        }
        if (!apartmentService.findById(id).isStatus()) {
            return BookingResult.ALREADY_BOOKED;
        }
        if (logInStatus) return BookingResult.SUCCESS;

        return BookingResult.LOGIN_REQUIRED;
    }
    public BookingResult clickSignIn(String username, String password) {
        UserService userService = new UserService();
        UserService.LoginResult loginResult = userService.login(username, password);

        if (loginResult == UserService.LoginResult.WRONG_PASSWORD){
            return BookingResult.WRONG_PASSWORD;
        }
        if (loginResult == UserService.LoginResult.USER_NOT_FOUND) {
            return BookingResult.LOGIN_REQUIRED;
        }
        if (loginResult == UserService.LoginResult.SUCCESS){
            logInStatus = true;
            return BookingResult.SUCCESS;
        }
        throw new IllegalStateException("Unexpected login result");
    }

    public boolean isValidDate(String date) {
        try {
            DateTimeFormatter dtf =
                    DateTimeFormatter.ofPattern("dd.MM.uuuu")
                            .withResolverStyle(java.time.format.ResolverStyle.STRICT);
            LocalDate.parse(date, dtf);
            return true;

        }
        catch (DateTimeParseException ex) {
            return false;
        }
    }
}
