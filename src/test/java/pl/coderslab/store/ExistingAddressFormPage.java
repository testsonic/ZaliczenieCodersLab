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

    public void CheckCreationAndCorrection(String nameAndSurname, String alias, String address, String city, String zipCode, String country, String phone) {

        String CreatedAddress = AllAddresses.get(AllAddresses.size() - 1).getText();
        String[] AddressParts = CreatedAddress.split("\\n");
        String[] ExpectedAddresses = {nameAndSurname, alias, address, city, zipCode, country, phone};
        Assert.assertEquals("Missing data in created address", ExpectedAddresses.length, AddressParts.length);
        Assert.assertArrayEquals("Wrong or missorder data", ExpectedAddresses, AddressParts);


    }

    public void DelateNewAddedAddress() {
        int BeforeDelationSize = AllAddresses.size();
        WebElement DelateBtn = driver.findElement(By.xpath("(//span[contains(text(),'Delete')])[" + AllAddresses.size() + "]"));
        DelateBtn.click();
        Assert.assertNotEquals("Failed to delate address", BeforeDelationSize, AllAddresses.size());
    }

    public void CheckDelatingAddress(String alias, String address, String city, String zipCode, String country, String phone) {
        for (int i = 0; i < AllAddresses.size() - 1; i++) {
            String CreatedAddress = AllAddresses.get(i).getText();
            Assert.assertFalse("Wrong address was delated", AllAddresses.contains(alias) && AllAddresses.contains(address) && AllAddresses.contains(city) && AllAddresses.contains(zipCode) && AllAddresses.contains(country) && AllAddresses.contains(phone));

        }


    }
}
