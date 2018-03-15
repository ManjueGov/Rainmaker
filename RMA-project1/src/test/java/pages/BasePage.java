package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Properties;
import java.util.concurrent.TimeUnit;

public class BasePage {

    protected void waitForElementToBeVisible(WebElement element, WebDriver webDriver) {
        WebDriverWait wait = new WebDriverWait(webDriver, Properties.waitTime);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForElementToBeClickable(WebElement element, WebDriver webDriver) {
        WebDriverWait wait = new WebDriverWait(webDriver, Properties.waitTime);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void waitForElementToBePresent(By by, WebDriver webDriver) {
        WebDriverWait wait = new WebDriverWait(webDriver, Properties.waitTime);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    protected boolean isDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected void enterText(WebElement webElement, String value, WebDriver driver) {
        waitForElementToBeVisible(webElement, driver);
        waitForElementToBeClickable(webElement, driver);
        webElement.clear();
        webElement.sendKeys(value);
    }

    protected void enterTextWithoutClearing(WebElement webElement, String value, WebDriver driver) {
        waitForElementToBeVisible(webElement, driver);
        waitForElementToBeClickable(webElement, driver);
        webElement.sendKeys(value);
    }

    protected void selectFromDropDown(WebElement webElement, String value, WebDriver driver) {
        waitForElementToBeVisible(webElement, driver);
        waitForElementToBeClickable(webElement, driver);
    }

    protected void clickOnButton(WebElement webElement, WebDriver driver) {
        waitForElementToBeVisible(webElement, driver);
        waitForElementToBeClickable(webElement, driver);
        try {
            webElement.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
            webElement.click();
        }
    }

    protected void uploadFile(WebElement element, String filePath, WebDriver driver) {
        waitForElementToBeVisible(element, driver);
        element.sendKeys(filePath);
    }

    protected void enterDate(WebElement webElement, String date, WebDriver driver) {
        waitForElementToBeVisible(webElement, driver);
        waitForElementToBeClickable(webElement, driver);
        webElement.clear();
        webElement.sendKeys(date, Keys.TAB);
    }

    protected void switchToNewlyOpenedWindow(WebDriver driver) {
        driver.manage().window().maximize();
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
    }

    protected void switchToPreviouslyOpenedWindow(WebDriver driver) {
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
    }

    protected void maximizeBrowserWindow(WebDriver webDriver) {
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    protected void refreshBrowserWindow(WebDriver webDriver) {
        webDriver.navigate().refresh();
    }

    protected String getEnvironmentURL() {

        String url = Properties.url;

        return url;
    }
}