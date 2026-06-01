package project;

import io.cucumber.java.en.*;
import org.junit.Assert;
import project.service.ListingService;

import java.math.BigDecimal;


public class ListingSteps {

    private long userId;
    private String url;
    private String city;
    private BigDecimal price;
    ListingService listingService = new ListingService();
    private ListingService.ListingResult listingResult;

    @Given("Потребителят с id {int} отваря сайта")
    public void openSite(long userId) {
    this.userId =userId;
    }

    @And("Натиска бутона за добавяне на апартамент")
    public void clickAddApartment() {
        listingResult=listingService.clickAddApartment(userId);
    }
    @And("Добавя снимка {string}")
    public void addPhotos(String url) {
    this.url=url;
    }
    @And("Попълва цена {string}")
    public void addPrice(String price) {
    this.price=new BigDecimal(price);
    }


    @And("Добавя град {string}")
    public void addCity(String city) {
    this.city=city;
    }


    @When("Потребителят натиска бутона Post")
    public void addApartment() {
        listingResult=listingService.addApartment(price, city, url);
    }

    @Then("На екрана се появява {string}.")
    public void checkPostingResult(String result) {
        Assert.assertEquals(result, listingResult.getMessage());
    }



}
