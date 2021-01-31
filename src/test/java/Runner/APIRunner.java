package Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/API",
        glue = "StepDefinitions/api",
        monochrome = false,
        dryRun = false,
        snippets = CucumberOptions.SnippetType.CAMELCASE
)


public class APIRunner {
}
