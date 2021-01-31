package Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"rerun:target/rerun.txt"},
        features = {"src/test/resources/com.WebOrder/"},
        glue = "StepDefinitions",
        monochrome = false,
        dryRun =false,   //it will run the feature file.
        tags = ""
)

public class CukesRunner {


}
