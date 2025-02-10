package pl.coderslab.store;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class AddressFormPage {
    private WebDriver driver;

    public AddressFormPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "addresses-link")
    private WebElement AdressesBtn;

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

    public void GoAndCreateNewAdresses(String alias, String Adress, String City, String Postcode, String Country, String PhoneNumb) {
        AdressesBtn.click();
        int BeforeCreationAddressesSize = AllAddresses.size();
        CreateNewAdressBtn.click();
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
