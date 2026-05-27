package project;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import project.service.BookingService;
import project.service.UserService;

import java.util.Objects;


public class BookingSteps {

    private String data;
    private String username;
    private String password;
    private String apartmentStatus;
    private String resultMessage;


    @Given("Потребител отваря обява на апартамент с id {int}")
    public void openApartmentPage(int idApartment) {
    }
    @And("Потребителят въвежда дата {string}")
    public void typeData(String data) {
        this.data = data;
    }

    @And("Апартамент е {string}")
    public void isAvailable(String status) {
        this.apartmentStatus = status;
    }

    @When("Потребителят натисне бутона за резервиране")
    public void clickBookApartment() {
        BookingService bookingService = new BookingService();
        if(data == null || data.isBlank()) {
            resultMessage = "Не е въведена дата";
            return;
        }
        if(!(bookingService.isValidData(data))) {
            resultMessage = "Грешна дата";
            return;
        }
        if (Objects.equals(apartmentStatus, "Зает")) {
            resultMessage = "Апартамент е зает";
            return;
        }
        return;

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
        UserService userService = new UserService();
        UserService.LoginResult loginResult = userService.login(username, password);

        if (loginResult == UserService.LoginResult.WRONG_PASSWORD){
            resultMessage = "Грешна парола";
            return;
        }
        if (loginResult == UserService.LoginResult.USER_NOT_FOUND) {
            resultMessage = "Не съществуващи данни. Създайте профил";
            return;
        }
        if (loginResult == UserService.LoginResult.SUCCESS){
            resultMessage = "Успешна заявка";
            return;
        }
    }

    @Then("На екрана се появява {string}")
    public void checkBookingResult(String result) {
        Assert.assertEquals(result, resultMessage);
    }


}
