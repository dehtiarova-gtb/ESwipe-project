package project.service;

import org.apache.commons.lang3.StringUtils;
import project.models.Apartment;
import project.repo.ApartmentRepo;

import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class ApartmentService {

    public Set<Apartment> find(
            String city,
            String area,
            boolean availabilityFilter) {

        return ApartmentRepo.getAll().stream()
                .filter(getApartmentPredicate(
                        city,
                        area,
                        availabilityFilter))
                .collect(Collectors.toSet());
        }

    private static Predicate<Apartment> getApartmentPredicate(
            String city,
            String area,
            boolean onlyAvailable) {

        return ap ->
                (StringUtils.isBlank(city)
                        || ap.getCity().contains(city))
                        &&
                (StringUtils.isBlank(area)
                        || ap.getArea().contains(area))
                        &&
                (!onlyAvailable
                        || ap.isStatus());
    }
    public Apartment findById(long id){
        return ApartmentRepo.getAll().stream()
                .filter(ap -> ap.getIdApartment()==id)
                .findFirst()
                .orElse(null);
    }
//        if (StringUtils.isNotBlank(city)&&StringUtils.isNotBlank(area) && onlyAvailable)
//            return ap -> ap.getCity()
//                    .contains(city)&&ap.getArea().contains(area)&&ap.isStatus();
//        if (StringUtils.isNotBlank(city)&&StringUtils.isNotBlank(area))
//            return ap -> ap.getCity()
//                    .contains(city)&&ap.getArea().contains(area);
//        if (StringUtils.isNotBlank(city) && onlyAvailable)
//            return ap -> ap.getCity()
//                    .contains(city)&&ap.isStatus();
//        if (StringUtils.isNotBlank(area) && onlyAvailable)
//            return ap -> ap.getArea().contains(area)&&ap.isStatus();
//
//        if(onlyAvailable)
//            return ap -> ap.isStatus();
//        if(StringUtils.isNotBlank(city))
//            return ap -> ap.getCity().contains(city);
//        if(StringUtils.isNotBlank(area))
//            return ap -> ap.getArea().contains(area);
//        return ap -> true;
//    }

}
