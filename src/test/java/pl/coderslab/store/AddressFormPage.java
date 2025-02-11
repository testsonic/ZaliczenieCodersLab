package pl.coderslab.store;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.NoSuchElementException;

public class AddressFormPage {
    private WebDriver driver;

    public AddressFormPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "//a[@data-link-action='add-address']")
    private WebElement CreateNewAdressBtn;

    @FindBy(id = "field-alias")
    private WebElement AliasInput;

    @FindBy(id = "field-address1")
    private WebElement AdressInput;

    @FindBy(id = "field-city")
    private WebElement CityInput;

    @FindBy(id = "field-postcode")
    private WebElement PostcodeInput;

    @FindBy(id = "field-id_country")
    private WebElement CountryList;

    @FindBy(id = "field-phone")
    private WebElement PhoneNumbInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement SubmitNewAddressBtn;

    @FindBy(className = "alert-success")
    private WebElement SuccesfullLoginAlert;

    @FindBy(className = "address-body")
    private List<WebElement> AllAddresses;

    public boolean isWebelementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void CheckBeforeCreation() { //Sprawdzenie czy istnieje już jakiś dodany adress,jezeli nie to tworzy i tak sie wysypie na delete
        List<WebElement> AddressesLinkBtn = driver.findElements(By.id("addresses-link"));
        if (!AddressesLinkBtn.isEmpty()) {
            driver.findElement(By.id("addresses-link")).click();
            System.out.println(driver.getCurrentUrl());
            Assert.assertEquals("Wrong url", "https://mystore-testlab.coderslab.pl/index.php?controller=addresses", driver.getCurrentUrl());
            CreateNewAdressBtn.click();
        } else {
            System.out.println("\u26A0 WARNING: There is no existing address. Creating new one, deleting will not work");
            driver.findElement(By.id("address-link")).click();
        }
    }

    public void GoAndCreateNewAdresses(String alias, String Adress, String City, String Postcode, String Country, String PhoneNumb) {
        CheckBeforeCreation();
        int BeforeCreationAddressesSize = AllAddresses.size();
        AliasInput.clear();
        AliasInput.sendKeys(alias);
        AdressInput.sendKeys(Adress);
        CityInput.sendKeys(City);
        PostcodeInput.sendKeys(Postcode);
        Select select = new Select(CountryList);
        select.selectByVisibleText(Country);
        PhoneNumbInput.sendKeys(PhoneNumb);
        SubmitNewAddressBtn.click();
        Assert.assertNotEquals("New adress wasn't created", BeforeCreationAddressesSize, BeforeCreationAddressesSize + 1);
    }

    public void SuccesfullLoginPrompt() {
        Assert.assertEquals("Missing successfull address creation prompt", "Address successfully added!", SuccesfullLoginAlert.getText());
    }


}
