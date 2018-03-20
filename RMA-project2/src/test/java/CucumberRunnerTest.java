import java.io.IOException;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;

@CucumberOptions(features = {"./src/test/resources/"})
@RunWith(MyCucumberRunner.class)
public class CucumberRunnerTest {

}