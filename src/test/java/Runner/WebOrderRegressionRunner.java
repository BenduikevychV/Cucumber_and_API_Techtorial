package Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty","html:target/cucumber-html-report","junit:target/cucumber.xml",
        "rerun:target/rerun.txt","json:target/cucumber.json"},
        features = "src/test/resources/API",
        glue = "StepDefinitions/api",
        monochrome = false,
        dryRun = false
)
public class WebOrderRegressionRunner {

}
//json:target/cucumber.json