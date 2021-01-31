package Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = "StepDefinitions",
        features = "src/test/resources/MyApp/MyAppDatabaseValidation.feature",
        dryRun = false
)
public class DBRunner {
}
