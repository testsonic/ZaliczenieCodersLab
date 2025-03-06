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

    public void CheckProductsAndDiscount(String discount, String item) {
        Assert.assertFalse("There is no any products on site", ProductsDisplayed.isEmpty());
        int a = 0;
        for (int i = 0; i < ProductsDisplayed.size(); i++) {
            System.out.println(ProductsDisplayed.get(i).getText());
            if (item.equals(ProductsDisplayed.get(i).getText())) {
                ProductsDisplayed.get(i).click();
            } else {
                a++;
                Assert.assertTrue("Item not found", ProductsDisplayed.size() - a != 0);
            }
        }

        if (IsElementDisplayed(By.cssSelector(".discount.discount-percentage"))&& !discount.equals("0%")) {
            WebElement ActualDiscount = driver.findElement(By.cssSelector(".discount.discount-percentage"));
            Assert.assertEquals("Wrong discount", "SAVE " + discount, ActualDiscount.getText());
        }
        else if (!discount.equals("0%")) {
            Assert.assertTrue("Missing discount",IsElementDisplayed(By.cssSelector(".discount.discount-percentage")));
        }
    }

}
