package weather.api.utils;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.get;


public class RestUtil {

    private  ResourceBundle endpoints = ResourceBundle.getBundle("endpoints");

    public void setBaseURI(String baseURI) {
        RestAssured.baseURI = baseURI;
    }

    public void setBasePath(String endpoint) {
        RestAssured.basePath = endpoints.getString(endpoint);
    }


    public String getWeatherSearchByCityNameEndpoint(String city) {
        return RestAssured.baseURI + RestAssured.basePath + "?q=" + city + "&APPID=" + endpoints.getString("APPID");
    }

    public String getWeatherSearchByCityIDEndpoint(String cityID) {
        return RestAssured.baseURI + RestAssured.basePath + "?id=" + cityID + "&APPID=" + endpoints.getString("APPID");
    }

    public String getWeatherSearchByCoordinates(String longitude, String latitude) {
        return RestAssured.baseURI + RestAssured.basePath + "?lat=" + latitude + "&lon=" + longitude + "&APPID=" + endpoints.getString("APPID");
    }

    public Response responseGET(String apiUrl) {
        return get(apiUrl);
    }

    public static JsonPath getJsonPath(Response response) {
        String json = response.asString();
        return new JsonPath(json);
    }

}
