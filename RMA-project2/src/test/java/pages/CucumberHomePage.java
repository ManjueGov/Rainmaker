package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CucumberHomePage
{

    @FindBy(linkText = "Docs") WebElement docsLink;

    @FindBy(css = "img[alt='Selenium Grid Logo']") WebElement clickOn;

    public void clickDocsLink()
    {
        docsLink.click();
    }

    public void click() {
        clickOn.click();
    }
}