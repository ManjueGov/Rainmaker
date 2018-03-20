package steps;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.remote.RemoteWebDriver;
import pages.CucumberHomePage;
import org.openqa.selenium.support.PageFactory;
import utils.DriverFactory;


import static org.junit.Assert.assertTrue;

public class CucumberSteps
{
    private Scenario scenario;

    @Before
    public void before(Scenario scenario) {
        this.scenario = scenario;
        this.scenario.getId();
        //get the browser for this id

    }
    public CucumberSteps() {
        int i=0;
    }

    RemoteWebDriver driver = new DriverFactory().init("chrome");
    CucumberHomePage cucumberHomePage = PageFactory.initElements(driver, CucumberHomePage.class);
    @Given("^I navigate to \"([^\"]*)\"$")
    public void i_navigate_to(String page) throws Throwable {
        driver.get(page);
    }

    @When("^I take a look at the Docs$")
    public void i_take_a_look_at_the_docs() throws Throwable {
        cucumberHomePage.clickDocsLink();
    }

    @Then("^I see a browser title containing \"([^\"]*)\"$")
    public void i_see_a_browser_title_containing(String text) throws Throwable {
        assertTrue(driver.getTitle().contains(text));
    }

    @When("^I take a look at the Selenium Webdriver Grid$")
    public void iTakeALookAtTheSeleniumWebdriverGrid() throws Throwable {
        cucumberHomePage.click();
    }
}