package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CucumberHomePage
{

    @FindBy(linkText = "Docs") WebElement docsLink;

    public void clickDocsLink()
    {
        docsLink.click();
    }

}