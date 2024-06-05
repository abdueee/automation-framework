package stepdefinitions;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features", // Path to the feature files
    glue = {"stepdefinitions"}, // Package containing step definitions
    plugin = {"pretty", "html:target/cucumber-reports"}, // Report generation
    monochrome = true // Output readable format
)
public class TestRunner {
}
