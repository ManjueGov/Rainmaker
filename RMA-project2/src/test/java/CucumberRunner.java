import cucumber.api.CucumberOptions;
import org.junit.runner.RunWith;

@CucumberOptions(features = {"src/test/resources"})
@RunWith(cucumber.api.junit.Cucumber.class)
public class CucumberRunner
{


}