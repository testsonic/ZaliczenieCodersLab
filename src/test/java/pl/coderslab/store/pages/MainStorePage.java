package pl.coderslab.store.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


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

    @FindBy(id = "quantity_wanted")
    private WebElement Amount;

    @FindBy(id = "group_1")
    private WebElement size;

    @FindBy(className = "add-to-cart")
    private WebElement AddBtn;

    @FindBy(xpath = "//a[@href='//mystore-testlab.coderslab.pl/index.php?controller=cart&action=show' and text()='Proceed to checkout']")
    private WebElement proceedToCheckoutBtn;

    @FindBy (xpath = "//a[@href='https://mystore-testlab.coderslab.pl/index.php?controller=order']")
    private WebElement finalProceedToCheckoutBtn;


    public boolean IsElementDisplayed(By selector) { //Sprawdzenie czy istnieje element
        try {
            WebElement element = driver.findElement(selector);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public void GoToStoreMainPage() {
        MainPageBtn.click();
    }

    public void CheckProductsAndDiscount(String discount, String UserAmount, String item, String UserSize) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // click an item
        for (int i = 1; i < ProductsDisplayed.size(); i++) {
            if (item.equals(ProductsDisplayed.get(i).getText())) {
                ProductsDisplayed.get(i).click();
            }
        }
        if (IsElementDisplayed(By.cssSelector(".discount.discount-percentage"))) {
            WebElement ActualDiscount = driver.findElement(By.cssSelector(".discount.discount-percentage"));
            Assert.assertEquals("Wrong ", "SAVE " + discount, ActualDiscount.getText());
        }


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

