package pl.coderslab.store.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pl.coderslab.store.pages.LoginPage;
import pl.coderslab.store.pages.MainStorePage;

public class ShopOrderPaySteps {
    private WebDriver driver;

    @Given("Logged user {string} {string} on store site")
    public void OpenBrowserAndLogin(String email, String password) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(); // Tryb Headless bez otwierania przeglądarki, usun argument options dla zwyklego wyswietalania
        driver.manage().window().maximize();
        driver.get("https://mystore-testlab.coderslab.pl");


        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginToCreateAddress(email, password);
        loginPage.CheckLogoutButtonVisibility();
    }

    @When("User checks {string} and order {string} of {string} in {string}")
    public void CheckDiscountAndOrderItem(String discount, String amount,String item,String size){
        MainStorePage mainStorePage = new MainStorePage(driver);
        mainStorePage.GoToStoreMainPage();
        mainStorePage.CheckProductsAndDiscount(discount,amount,item,size);
    }
}
