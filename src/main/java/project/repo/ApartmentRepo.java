package project.repo;

import project.models.Apartment;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class ApartmentRepo {

    public static Set<Apartment> getAll(){
        HashSet<Apartment> apartments = new HashSet<>();
        Apartment apartment1 = new Apartment
                (1L,"2-bedroom apartment","София","Младост",
                        new BigDecimal("500"),"https://example1.com",false);
        Apartment apartment2 = new Apartment
                (2L,"1-bedroom apartment","Пловдив","Кършияка",
                        new BigDecimal("300"),"https://example2.com",true);
        Apartment apartment3 = new Apartment
                (3L,"4-bedroom apartment","Пловдив","Мараша",
                        new BigDecimal("600"),"https://example3.com",true);

        apartments.add(apartment1);
        apartments.add(apartment2);
        apartments.add(apartment3);
        return apartments;
    }
}
