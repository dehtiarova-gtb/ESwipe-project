package project;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/project"},
        glue = {"project"},
        plugin = {
        "pretty",
        "html:target/cucumber-report.html"},
        monochrome = true)
public class CucumberStarter {
}
