package pl.coderslab.store.pages;

import io.cucumber.java.ca.Quan;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainStorePage {
    private WebDriver driver;

    public MainStorePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "logo")
    private WebElement MainPageBtn;

    @FindBy(className = "product-title")
    private List<WebElement> ProductsDisplayed;

    @FindBy (id = "quantity_wanted")
    private WebElement Amount;

    @FindBy (id = "group_1")
    private WebElement size;

    @FindBy (xpath = "//button[@type='submit']")
    private WebElement AddToCartBtn;


    public boolean IsElementDisplayed(By selector) { //Sprawdzenie czy istnieje element
        try {
            WebElement discount = driver.findElement(selector);
            return true;
        } catch (Exception e) {
            return false;
        }


    }

    public void GoToStoreMainPage() {
        MainPageBtn.click();
    }

    public void CheckProductsAndDiscount(String discount,String amount, String item, String UserSize) {


        // click an item
        for (int i = 1; i < ProductsDisplayed.size(); i++) {
            if (item.equals(ProductsDisplayed.get(i).getText())) {
                ProductsDisplayed.get(i).click();
            }
        }
        if (IsElementDisplayed(By.cssSelector(".discount.discount-percentage"))) {
            WebElement ActualDiscount = driver.findElement(By.cssSelector(".discount.discount-percentage"));
            Assert.assertEquals("NIE ZNALAZLO!!", "SAVE " + discount, ActualDiscount.getText());
        }
        Amount.click();
        Amount.sendKeys(Keys.CONTROL + "A");
        Amount.sendKeys(amount);
        Select select = new Select(size);
        select.selectByVisibleText(UserSize);


        AddToCartBtn.click();
    }
}
