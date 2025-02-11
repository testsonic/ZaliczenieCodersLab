package pl.coderslab.store;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CreateNewAddressSteps {
    WebDriver driver;

    @Given("an open browser with opened store site")
    public void OpenBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://mystore-testlab.coderslab.pl");


    }

    @And("logged user with credentials:{string}:{string}")
    public void LoginToAccount(String email, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs(email, password);
        loginPage.CheckLogoutButtonVisibility();
    }

    @When("User creating new address with input {string} {string} {string} {string} {string} {string}")
    public void CreateNewAddress(String alias, String address, String city, String zipCode, String country, String phone) {
        AddressFormPage addressFormPage = new AddressFormPage(driver);
        addressFormPage.GoAndCreateNewAdresses(alias, address, city, zipCode, country, phone);
        addressFormPage.SuccesfullLoginPrompt();
    }

    @Then("User sees new address {string} {string} {string} {string} {string} {string} {string}")
    public void CheckNewAddedAddress(String nameAndSurname, String alias, String address, String city, String zipCode, String country, String phone) {
        ExistingAddressFormPage existingAddressFormPage = new ExistingAddressFormPage(driver);
        existingAddressFormPage.CheckCreationAndCorrection(alias, nameAndSurname, address, city, zipCode, country, phone);
    }

    @And("User deleting new added address")
    public void DeleteAndCheckAddress() {
        ExistingAddressFormPage existingAddressFormPage = new ExistingAddressFormPage(driver);
        existingAddressFormPage.DeleteNewAddedAddress();

    }

    @And("Correct address was deleted {string} {string} {string} {string} {string} {string}")
    public void CheckCorrectAddressDelete(String alias, String address, String city, String zipCode, String country, String phone) {
        ExistingAddressFormPage existingAddressFormPage = new ExistingAddressFormPage(driver);
        existingAddressFormPage.CheckDeletedAddress(alias, address, city, zipCode, country, phone);

    }

    @And("close browser")
    public void tearDown() {
        driver.quit();
    }
}
