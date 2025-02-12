package pl.coderslab.store.tests;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/Cucumber/Features/shop-order-and-check-sweater.feature", plugin = {"pretty", "html:out.html"})
public class ShopOrderPayTest {


}
