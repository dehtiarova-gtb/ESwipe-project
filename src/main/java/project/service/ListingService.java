package project.service;

import project.models.Role;

import java.math.BigDecimal;
import java.util.Set;

public class ListingService {
    public enum ListingResult {
        SUCCESS("Обявата е публикувана успешно"),
        PHOTO_REQUIRED("Добавете снимка на апартамент"),
        INVALID_CITY("Неподдържан град"),
        INVALID_PRICE("Цената трябва да е по-голяма от 0"),
        HOST_REQUIRED("Кандидатствайте за HOST партньор"),
        DATA_REQUIRED("Добавете информация за апартамент");

        private final String message;

        ListingResult(String message) {
            this.message = message;
        }
        public String getMessage() {
            return message;
        }
    }
    public ListingResult clickAddApartment(long userId) {

        if(!hasHostRole(userId)){
            return ListingResult.HOST_REQUIRED;
        }
        return ListingResult.DATA_REQUIRED;
    }
    public ListingResult addApartment(BigDecimal price, String city, String url) {

        if(!isValidPrice(price)) {
            return ListingResult.INVALID_PRICE;
        }
        if (!isValidCity(city)) {
            return ListingResult.INVALID_CITY;
        }
        if(url==null||url.isBlank()) {
            return ListingResult.PHOTO_REQUIRED;
        }
        return ListingResult.SUCCESS;
    }



    private static final Set<String> SUPPORTED_CITIES = Set.of("Пловдив","София","Варна");
    UserService userService = new UserService();

    public boolean isValidCity(String city) {
        return SUPPORTED_CITIES.contains(city);
    }
    public boolean hasHostRole(long userID){
        return userService.
                getRoles(userID).
                contains(Role.HOST);
    }
    public boolean isValidPrice(BigDecimal price){
        return price.
                compareTo(BigDecimal.ZERO)>0;
    }


}
