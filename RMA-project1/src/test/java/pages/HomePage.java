package pages;

import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class HomePage extends BasePage {
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void visitWebsite() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

}
