package weather.api.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import weather.api.configuration.TestConfiguration;
import weather.api.models.Search;
import weather.api.utils.RestHelperMethods;
import weather.api.utils.RestUtil;


public class WSStepDefinition {

    private TestConfiguration testConfiguration;
    private RestUtil restUtil;
    private Search searchBy;
    private static Response response;

    public static Response getResponse() {
        return response;
    }

    public WSStepDefinition() {
        testConfiguration = new TestConfiguration();
        restUtil = new RestUtil();
        restUtil.setBaseURI(testConfiguration.getHost());
        searchBy = new Search();
    }


    @Given("^A request is performed to the \"([^\"]*)\" endpoint$")
    public void aRequestIsPerformedToEndpoint(String endpoint) {
        restUtil.setBasePath(endpoint.toLowerCase());
    }

    @And("^Using as city name \"([^\"]*)\"$")
    public void usingAsCityName(String city) {
        searchBy.setSearchBy("city name");
        searchBy.setCityname(city);
    }

    @And("^Using as city id \"([^\"]*)\"$")
    public void usingAsCityId(String cityId) {
        searchBy.setSearchBy("city id");
        searchBy.setCityId(cityId);
    }

    @When("^The GET http request is performed$")
    public void theGETHttpRequestIsPerformed() {
        switch (searchBy.getSearchBy()) {
            case "city name":
                response = restUtil.responseGET(restUtil.getWeatherSearchByCityNameEndpoint(searchBy.getCityname()));
                break;
            case "city id":
                response = restUtil.responseGET(restUtil.getWeatherSearchByCityIDEndpoint(searchBy.getCityId()));
                break;
            case "coordinates":
                response = restUtil.responseGET(restUtil.getWeatherSearchByCoordinates(searchBy.getLongitude(), searchBy.getLatitude()));
        }

    }

    @Then("^The response status code is \"([^\"]*)\"$")
    public void theResponseStatusIs(String statusCode) {
        RestHelperMethods.checkStatusCodeIs(response, statusCode);
    }

    @And("^The response message code is \"([^\"]*)\"$")
    public void theResponseBodyIs(String responseBody) {
        RestHelperMethods.checkStatusCodeMessage(response, responseBody);
    }

    @Then("^The longitude value is \"([^\"]*)\"$")
    public void theLongitudeValueIs(String expectedLongitude) {
        String actualLongitude = RestHelperMethods.getLongitudeValue(response);
        Assert.assertTrue("The longitude value should be: " + expectedLongitude + " but the actual value is: " + actualLongitude
                , expectedLongitude.equals(actualLongitude));
    }

    @And("^The latitude value is \"([^\"]*)\"$")
    public void theLatitudeValueIs(String expectedLatitude) {
        String actualLatitude = RestHelperMethods.getLatitudeValue(response);
        Assert.assertTrue("The latitude value should be: " + expectedLatitude + " but the actual value is: " + actualLatitude
                , expectedLatitude.equals(actualLatitude));

    }

    @And("^Using as city longitude \"([^\"]*)\"$")
    public void usingAsCityLongitude(String longitude) {
        searchBy.setSearchBy("coordinates");
        searchBy.setLongitude(longitude);

    }

    @And("^Using as city latitude \"([^\"]*)\"$")
    public void usingAsCityLatitude(String latitude) {
        searchBy.setLatitude(latitude);
    }

    @And("^The city name is \"([^\"]*)\"$")
    public void theCityNameIs(String expectedCity) {
        String actualCity = RestHelperMethods.getCityValue(response);
        Assert.assertTrue("The city should be: " + expectedCity + " but the actual value is: " + actualCity
                , expectedCity.equals(actualCity));
    }
}
