package project;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import project.models.Apartment;
import project.service.ApartmentService;

import java.util.Set;
import static org.junit.Assert.*;

public class SearchSteps {

    private String city;
    private String area;
    private boolean status;
    private Set<Apartment> apartments;
    @Given("Потребител отваря екрана за търсене на апартамент")
    public void openSearchScreen() {

    }
    @When("потребителя въвведе град {string}")
    public void typeCity(String city) {
        this.city=city;

    }
    @When("потребителя въвведе район {string}")
    public void typeArea(String area) {
        this.area=area;
    }
    @When("Натисне бутона за търсене")
    public void clickSearch() {
        ApartmentService apartmentService = new ApartmentService();
        apartments = apartmentService.find(city,area,status);
    }
    @Then("На екрана се появява {int} запис")
    public void checkResultList(int count) {
        assertNotNull(apartments);
        assertEquals(count, apartments.size());
    }
    @Then("Град на апартамент е {string}")
    public void checkCityName(String expectedCity) {
        assertEquals(expectedCity,apartments.stream().findFirst().orElse(null).getCity());
    }

    @And("Статус на апартамент е {string}")
    public void checkApartmentStatus(String expectedStatus) {
        String actualStatus = apartments.stream().findFirst().orElse(null).
                isStatus()?"Свободен" : "Зает";
        assertEquals(expectedStatus, actualStatus);

    }


}
