package weather.api.utils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;

public class RestHelperMethods {

    public static void checkStatusCodeMessage(Response response, String statusCodeMessage) {
        Assert.assertTrue("The actual status message is: " + response.getStatusLine()
                        + " but the expected status message is: " + statusCodeMessage
                , response.getStatusLine().toLowerCase().contains(statusCodeMessage.toLowerCase()));
    }

    public static void checkStatusCodeIs(Response response, String statusMessage) {
        Assert.assertTrue("The actual status code is: " + response.getStatusCode()
                        + " but the expected status code is: " + identifyStatusCode(statusMessage)
                , response.getStatusCode() == identifyStatusCode(statusMessage));
    }

    private static int identifyStatusCode(String responseMessage) {
        int status;

        switch (responseMessage.toLowerCase()) {
            case "ok":
                status = 200;
                break;
            case "bad request":
                status = 400;
                break;
            case "server error":
                status = 500;
                break;
            default:
                status = 0;
        }
        return status;
    }

    public static String getLongitudeValue(Response response) {
        JsonPath jsonResponse = RestUtil.getJsonPath(response);
        if (jsonResponse.get("coord.lon") instanceof Integer) {
            return Integer.toString(jsonResponse.get("coord.lon"));
        } else
            return Float.toString(jsonResponse.get("coord.lon"));
    }

    public static String getLatitudeValue(Response response) {
        JsonPath jsonResponse = RestUtil.getJsonPath(response);
        if (jsonResponse.get("coord.lat") instanceof Integer) {
            return Integer.toString(jsonResponse.get("coord.lat"));
        } else
            return Float.toString(jsonResponse.get("coord.lat"));
    }

    public static String getCityValue(Response response) {
        JsonPath jsonResponse = RestUtil.getJsonPath(response);
        return jsonResponse.get("name");
    }

    public static String getCountryCodeValue(Response response) {
        JsonPath jsonResponse = RestUtil.getJsonPath(response);
        return jsonResponse.get("sys.country");
    }
}
