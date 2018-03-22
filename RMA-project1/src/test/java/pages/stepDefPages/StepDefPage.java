package pages.stepDefPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class StepDefPage extends BasePage {
    private WebDriver driver;
    public StepDefPage(WebDriver driver){ this.driver = driver;}

    @FindBy(css = "input[id='username']")
    private WebElement userName;

    @FindBy(css = "input[id='password']")
    private WebElement password;

    @FindBy(xpath = "//*[@id=\"root\"]//div/form[1]//div[4]/div[1]/button[1]")
    private WebElement signInButton;

    @FindBy(xpath = "//div[contains(text(), 'My Tasks')]")
    private WebElement myTask;

    @FindBy(xpath = "//i[text()='more_vert']")
    private WebElement profile;

    @FindBy(xpath = "//div[text()='Sign Out']")
    private WebElement signOutButton;

    @FindBy(linkText = "Docs")
    private WebElement docsLink;

    @FindBy(css = "img[alt='Selenium Grid Logo']")
    private WebElement gridLink;

    public void login() {
        enterText(userName, "murali", driver);
        enterText(password, "12345678", driver);
        clickOnButton(signInButton, driver);
    }

    public void verifies() {
        isDisplayed(myTask);
    }

    public void logout() {
        clickOnButton(profile, driver);
        clickOnButton(signOutButton, driver);
    }

    public void goTo(String url) {
        driver.get(url);
    }

    public void lookAt() {
        docsLink.click();
    }

    public void containString(String contains) {
        driver.getTitle().contains(contains);
    }

    public void lookAtLink() {
        gridLink.click();
    }
}
