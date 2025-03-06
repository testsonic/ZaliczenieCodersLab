package pl.coderslab.store.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {
    private WebDriver driver;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "quantity_wanted")
    private WebElement Amount;

    @FindBy(id = "group_1")
    private WebElement size;

    @FindBy(className = "add-to-cart")
    private WebElement AddBtn;

    @FindBy(xpath = "//a[@href='//mystore-testlab.coderslab.pl/index.php?controller=cart&action=show' and text()='Proceed to checkout']")
    private WebElement proceedToCheckoutBtn;

    @FindBy(xpath = "//a[@href='https://mystore-testlab.coderslab.pl/index.php?controller=order']")
    private WebElement finalProceedToCheckoutBtn;


    public void OrderItem(String UserAmount, String UserSize) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Select select = new Select(size);
        select.selectByVisibleText(UserSize);
        wait.until(ExpectedConditions.elementToBeClickable(AddBtn));
        Amount.sendKeys(Keys.CONTROL + "a");
        Amount.sendKeys(UserAmount);
        AddBtn.click();
        wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(finalProceedToCheckoutBtn)).click();
    }
}
