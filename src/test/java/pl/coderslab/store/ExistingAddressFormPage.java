package pl.coderslab.store;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ExistingAddressFormPage {
    private WebDriver driver;

    public ExistingAddressFormPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "address-body")
    private List<WebElement> AllAddresses;

    public void CheckCreationAndCorrection(String nameAndSurname, String alias, String address, String city, String zipCode, String country, String phone){
        String CreatedAddress = AllAddresses.get(AllAddresses.size() - 1).getText();
        String[] AddressParts = CreatedAddress.split("\\n");
        String[] ExpectedAddresses = {nameAndSurname,alias,address,city,zipCode,country,phone};
        Assert.assertArrayEquals("Missing ",ExpectedAddresses,AddressParts);

    }

    public void GetExistingAddresses() {
        WebElement DelateBtn = driver.findElement(By.xpath("(//span[contains(text(),'Delete')])[" + AllAddresses.size() + "]"));
        DelateBtn.click();
    }


}
