package weather.api.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import org.junit.Assert;
import weather.api.utils.RestHelperMethods;


public class HomeworkSteps {

    Response response = WSStepDefinition.getResponse();

    @And("^The country code is \"([^\"]*)\"$")
    public void theCountryNameIs(String expectedCountryCode) {
        String actualCountryCode = RestHelperMethods.getCountryCodeValue(response);
        Assert.assertTrue("The city should be: " + expectedCountryCode + " but the actual value is: " + actualCountryCode
                , expectedCountryCode.equals(actualCountryCode));
    }

    @Then("^The city name contains \"([^\"]*)\"$")
    public void theCityNameContainsValue(String expectedValue) {
        String actualCity = RestHelperMethods.getCityValue(response);
        Assert.assertTrue("The city should contain: " + expectedValue, actualCity.contains(expectedValue));
    }
}
