package project;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import project.service.BookingService;



public class BookingSteps {

    private String date;
    private String username;
    private String password;
    private long idApartment;
    BookingService bookingService = new BookingService();
    private BookingService.BookingResult bookingResult;

    @Given("Потребител отваря обява на апартамент с id {int}")
    public void openApartmentPage(int idApartment) {
        this.idApartment = idApartment;
    }
    @And("Потребителят въвежда дата {string}")
    public void typeDate(String data) {
        this.date = data;
    }

    @When("Потребителят натисне бутона за резервиране")
    public void clickBookApartment() {
        bookingResult= bookingService.clickBookApartment(idApartment, date);
    }

    @Then("На екрана се появява форма за влизане")
    public void signInForm() {

    }

    @When("Потребителят въвежда юзернейм {string}")
    public void addUsername(String username) {
        this.username = username;
    }

    @And("Потребителят въвежда парола {string}")
    public void addPassword(String password) {
        this.password = password;
    }

    @And("Потребителят натиска бутона Влез")
    public void clickSignIn() {
       bookingResult= bookingService.clickSignIn(username, password);
    }

    @Then("На екрана се появява {string}")
    public void checkBookingResult(String result) {
        Assert.assertEquals(result, bookingResult.getMessage());
    }


}
