package steps;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import pages.StepDefPage;

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
    }
}
