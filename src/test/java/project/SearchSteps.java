package project;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import project.models.Apartment;
import project.service.ApartmentService;

import java.util.Objects;
import java.util.Set;
import static org.junit.Assert.*;

public class SearchSteps {

    private String city;
    private String area;
    private boolean availabilityFilter;
    private Set<Apartment> apartments;

    private void assertApartmentsFound() {
        assertFalse(apartments.isEmpty());
    }

    @Given("Потребител отваря екрана за търсене на апартамент")
    public void openSearchScreen() {

    }
    @When("потребителя въвведе град {string}")
    public void typeCity(String city) {
        this.city=city;

    }
    @And("потребителя въвведе район {string}")
    public void typeArea(String area) {
        this.area=area;
    }
    @When("потребителя избира филтър {string}")
    public void chooseStatus(String status) {
        if(Objects.equals(status, "Свободен"))  this.availabilityFilter =true;
        if(Objects.equals(status, "Зает"))  this.availabilityFilter =false;

    }
    @When("Натисне бутона за търсене")
    public void clickSearch() {
        ApartmentService apartmentService = new ApartmentService();
        apartments = apartmentService.find(city,area, availabilityFilter);
    }
    @Then("На екрана се появява {int} запис")
    public void checkResultList(int count) {
        assertEquals(count, apartments.size());
    }
    @Then("Град на апартамент е {string}")
    public void checkCityName(String expectedCity) {
        assertApartmentsFound();

        assertTrue(
                apartments.stream()
                        .allMatch(a -> a.getCity().equals(expectedCity))
        );
    }
    @And("Район на апартамент е {string}")
    public void checkAreaName(String expectedArea) {
        assertApartmentsFound();

        assertTrue(
                apartments.stream()
                        .allMatch(a -> a.getArea().equals(expectedArea))
        );
    }

    @And("Статус на апартамент е {string}")
    public void checkApartmentStatus(String expectedStatus) {
        assertApartmentsFound();

        boolean expected =
                expectedStatus.equals("Свободен");
        assertTrue(
                apartments.stream()
                        .allMatch(a -> a.isStatus() == expected)
        );

    }



}
