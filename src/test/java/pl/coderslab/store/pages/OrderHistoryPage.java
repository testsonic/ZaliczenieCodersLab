package pl.coderslab.store.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.coderslab.store.cfg.GlobalData;

import java.util.List;

public class OrderHistoryPage {
    private WebDriver driver;

    public OrderHistoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "account")
    private WebElement AccountBtn;

    @FindBy(id = "history-link")
    private WebElement OrderHistoryBtn;



    public void GoTOrderHistory() {
        AccountBtn.click();
        OrderHistoryBtn.click();

    }

    public void CheckStatusAndPrice() {


        String priceXPath = "//tr[th[text()='" + GlobalData.orderReference + "']]/td[2]"; // cena pobierana z order history
        WebElement priceInOrderHistory = driver.findElement(By.xpath(priceXPath));
        String statusXPath = "//tr[th[text()='" + GlobalData.orderReference + "']]/td[4]"; // cena pobierana z order history
        WebElement statusInOrderHistory = driver.findElement(By.xpath(statusXPath));

        Assert.assertTrue("Missing order in order history", priceInOrderHistory.isDisplayed());
        Assert.assertEquals("Price in order history is not correct",GlobalData.price,priceInOrderHistory.getText());
        Assert.assertTrue("Missing payment status in order history", statusInOrderHistory.isDisplayed());
        Assert.assertEquals("Status in order history is not correct",GlobalData.expectedStatus,statusInOrderHistory.getText());




    }


}








