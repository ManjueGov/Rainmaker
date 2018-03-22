package steps.stepDefinations;

import cucumber.api.java8.En;
import pages.stepDefPages.StepDefPage;
import steps.BaseSteps;

public class StepDef extends BaseSteps implements En {
    public StepDef() {
        Given("^(.*) logs in$", (String currentUser) -> {
            pageStore.get(StepDefPage.class).login();
        });
        And("^verifies dashboard$", () -> {
            pageStore.get(StepDefPage.class).verifies();
        });
        Then("^logs out$", () -> {
            pageStore.get(StepDefPage.class).logout();
        });
        //grid
        Given("^I navigate to \"([^\"]*)\"$", (String url) -> {
            pageStore.get(StepDefPage.class).goTo(url);
        });
        When("^I take a look at the Docs$", () -> {
            pageStore.get(StepDefPage.class).lookAt();
        });
        Then("^I see a browser title containing \"([^\"]*)\"$", (String contains) -> {
            pageStore.get(StepDefPage.class).containString(contains);
        });
        When("^I take a look at the Selenium Webdriver Grid$", () -> {
            pageStore.get(StepDefPage.class).lookAtLink();
        });
    }
}
