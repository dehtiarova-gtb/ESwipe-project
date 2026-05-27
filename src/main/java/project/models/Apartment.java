package project.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Apartment {
    private long idApartment;
    private String title;
    private String city;
    private String area;
    private BigDecimal price_per_day;
    private String image_url;
    private boolean status;


    //FK user_idк


}
