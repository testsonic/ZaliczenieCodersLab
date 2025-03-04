package pl.coderslab.store.pages;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.nio.file.Files;
import java.io.File;
import java.text.SimpleDateFormat;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.nio.file.Path;
import java.nio.file.Paths;



import java.time.Duration;


public class FinalizationOrderPage {
    private WebDriver driver;

    public FinalizationOrderPage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "id-address-delivery-address-17886")
    private WebElement deliveryAddress;

    @FindBy(name = "confirm-addresses")
    private WebElement confirmAddressBtn;

    @FindBy(id = "delivery_option_8") //
    private WebElement selfPickUpBtn;

    @FindBy(name = "confirmDeliveryOption")
    private WebElement confirmDeliveryBtn;

    @FindBy(xpath = "//label[@for='payment-option-1']")
    private WebElement payByCheckRadioBtn;

    @FindBy(id = "conditions_to_approve[terms-and-conditions]")
    private WebElement termsOfServiceBtn;

    @FindBy(xpath = "//button[@type='submit' and contains(text(), 'Place order')]")
    private WebElement placeOrderBtn;


    public void ShippingAndPayment() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        deliveryAddress.click();
        confirmAddressBtn.click();
        if (!selfPickUpBtn.isSelected()) {
            selfPickUpBtn.click();
        }
        wait.until(ExpectedConditions.elementToBeClickable(confirmDeliveryBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(payByCheckRadioBtn)).click();
        termsOfServiceBtn.click();
        System.out.println(placeOrderBtn.isDisplayed());
        wait.until(ExpectedConditions.elementToBeClickable(placeOrderBtn)).click();


    }

    public void TakeScreenshot() throws IOException {
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File destFile = new File("screenshot_" + timestamp + ".png");
        FileUtils.copyFile(screenshot,destFile);


    }

}
