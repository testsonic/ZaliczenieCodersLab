package pl.coderslab.store.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pl.coderslab.store.pages.FinalizationOrderPage;
import pl.coderslab.store.pages.LoginPage;
import pl.coderslab.store.pages.MainStorePage;
import pl.coderslab.store.pages.ProductPage;

import java.io.IOException;

public class ShopOrderPaySteps {
    private WebDriver driver;

    @Given("Logged user {string} {string} on store site")
    public void OpenBrowserAndLogin(String email, String password) {
        ChromeOptions options = new ChromeOptions();
        //  options.addArguments("--headless");
        driver = new ChromeDriver(); // Tryb Headless bez otwierania przeglądarki, usun argument options dla zwyklego wyswietalania
        driver.manage().window().maximize();
        driver.get("https://mystore-testlab.coderslab.pl");


        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginToCreateAddress(email, password);
        loginPage.CheckLogoutButtonVisibility();
    }

    @When("User checks {string} of {string}")
    public void CheckDiscountAndOrderItem(String discount, String item) {
        MainStorePage mainStorePage = new MainStorePage(driver);
        mainStorePage.GoToStoreMainPage();
        mainStorePage.CheckProductsAndDiscount(discount, item);


    }

    @And("User order {string} in {string}")
    public void OrderItem(String amount, String size) {
        ProductPage productPage = new ProductPage(driver);
        productPage.OrderItem(amount,size);

    }

    @And("'Pay by check' with 'pick up in store'")
    public void OrderFinalization() {
        FinalizationOrderPage finalizationOrderPage = new FinalizationOrderPage(driver);
        finalizationOrderPage.ShippingAndPayment();
    }

    @Then("Take a screenshot with order confirmation and price")
    public void TakeOrderScreenshoot() throws IOException {
        FinalizationOrderPage finalizationOrderPage = new FinalizationOrderPage(driver);
        finalizationOrderPage.TakeOrderConfirmationScreenshot();

    }

    @And("Close browser")
    public void CloseBrowser() {
        driver.quit();
    }
}
