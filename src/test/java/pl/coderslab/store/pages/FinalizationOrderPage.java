package pl.coderslab.store.pages;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;


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

    @FindBy(id = "content")
    private WebElement orderConfirmation;


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

    public void TakeOrderConfirmationScreenshot() throws IOException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.body.style.zoom='60%'"); // zoom strony
        File screenshot = orderConfirmation.getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File destFile = new File("screenshot_" + timestamp + ".png");
        FileUtils.copyFile(screenshot, destFile);


    }

}
