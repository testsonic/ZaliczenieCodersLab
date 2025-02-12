package pl.coderslab.store.pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@title ='Log in to your customer account']")
    private WebElement GoToLoginPageBtn;

    @FindBy(id = "field-email")
    private WebElement EmailLoginInput;

    @FindBy(id = "field-password")
    private WebElement PasswordLoginInput;

    @FindBy(id = "submit-login")
    private WebElement SubmitLoginBtn;

    @FindBy(className = "logout")
    private WebElement LogoutBtn;


    public void loginToCreateAddress(String email, String password) {
        GoToLoginPageBtn.click();
        EmailLoginInput.sendKeys(email);
        PasswordLoginInput.sendKeys(password);
        SubmitLoginBtn.click();
    }

    public void CheckLogoutButtonVisibility() {
        Assert.assertTrue("Failed to login", LogoutBtn.isDisplayed());
        ;
    }

}
