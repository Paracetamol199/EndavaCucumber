package weather.api.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@CucumberOptions(plugin = {"pretty", "html:target/cucumber", "json:target/cucumber.json"},
        features = "src/test/resources/features",
        tags = {"@Homework"})

public class HomeworkRunner {

}
