package weather.api;

import java.util.ResourceBundle;

public class TestConfiguration {

    private ResourceBundle defaultTestConfiguration = ResourceBundle.getBundle("configuration");

    private String host;

    public TestConfiguration() {
        if (System.getProperty("host") != null){
            setHost(System.getProperty("host"));
        }
        else {
            setHost(defaultTestConfiguration.getString("host"));
        }
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getHost() {
        return host;
    }
}
