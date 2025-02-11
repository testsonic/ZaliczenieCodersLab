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

    @FindBy(className = "alert-success")
    private WebElement DeletedAddressAlert;

    public void CheckCreationAndCorrection(String nameAndSurname, String alias, String address, String city, String zipCode, String country, String phone) {

        String CreatedAddress = AllAddresses.get(AllAddresses.size() - 1).getText();
        String[] AddressParts = CreatedAddress.split("\\n");
        String[] ExpectedAddresses = {nameAndSurname, alias, address, city, zipCode, country, phone};
        Assert.assertEquals("Missing data or address was not created", ExpectedAddresses.length, AddressParts.length);
        Assert.assertArrayEquals("Wrong or missorder data", ExpectedAddresses, AddressParts);


    }

    public void DeleteNewAddedAddress() {
        int BeforeDeletionSize = AllAddresses.size();
        Assert.assertFalse("Test created first address in user account, deleting will fail", BeforeDeletionSize == 1); // sfailuje bo strona nie pozwala na usunięcie wszystkich adresów
        WebElement DeleteBtn = driver.findElement(By.xpath("(//span[contains(text(),'Delete')])[" + AllAddresses.size() + "]"));
        DeleteBtn.click();
        Assert.assertNotEquals("Failed to delate address", BeforeDeletionSize, AllAddresses.size());
    }

    public void CheckDeletedAddress(String alias, String address, String city, String zipCode, String country, String phone) {
        for (int i = 0; i < AllAddresses.size() - 1; i++) {
            Assert.assertFalse("Wrong address was deleted", AllAddresses.contains(alias) && AllAddresses.contains(address) && AllAddresses.contains(city) && AllAddresses.contains(zipCode) && AllAddresses.contains(country) && AllAddresses.contains(phone));

        }


    }
}
