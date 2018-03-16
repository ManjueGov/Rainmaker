package utils;

import gherkin.deps.com.google.gson.JsonArray;
import gherkin.deps.com.google.gson.JsonObject;
import gherkin.deps.com.google.gson.JsonParser;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.*;
import java.net.URL;

public class JUnitSequential {
    private String platform;
    private String browserName;
    private String browserVersion;
    private String json;
    private JsonArray myArray;

    @Before
    public void setUp() throws Exception {
        //with json
        FileInputStream fin = new FileInputStream(new File(System.getProperty("user.dir")+"//src//test//resources//simpleJSON.json"));
        InputStreamReader in = new InputStreamReader(fin);
        BufferedReader bufferedReader = new BufferedReader(in);
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            sb.append(line);
        }
        json = sb.toString();
        JsonParser parser = new JsonParser();
        JsonObject myObject = (JsonObject) parser.parse(json);
        myArray = myObject.get("capabilities").getAsJsonArray();
    }

    private WebDriver initializeDriver(String browserName) throws Exception{
        WebDriver driver=null;
            DesiredCapabilities capability = new DesiredCapabilities();
            capability.setCapability("browser", browserName);
            if(browserName.equalsIgnoreCase("chrome"))
            driver = new RemoteWebDriver(new URL("http://172.16.2.178:4444/wd/hub"), DesiredCapabilities.chrome());
            else if(browserName.equalsIgnoreCase("firefox"))
                driver = new RemoteWebDriver(new URL("http://172.16.2.178:4444/wd/hub"), DesiredCapabilities.firefox());
            else if(browserName.equalsIgnoreCase("ie"))
                driver = new RemoteWebDriver(new URL("http://172.16.2.178:4444/wd/hub"), DesiredCapabilities.internetExplorer());
            return driver;
    }
    @Test
    public void testSimple() throws Exception {
        for (int i=0; i<myArray.size(); i++){
            testCase1(myArray.get(i).getAsJsonObject().get("browserName").getAsString());
            testCase2(myArray.get(i).getAsJsonObject().get("browserName").getAsString());
        }
    }

    private void testCase1(String browserName) throws Exception{
        WebDriver driver=initializeDriver(browserName);
        driver.get("https://cucumber.io/");
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        String title = driver.getTitle();
        System.out.println("Page title is: " + title);
        WebElement element = driver.findElement(By.linkText("Docs"));
        element.click();
        driver = new Augmenter().augment(driver);
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, new File("Screenshot.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver.quit();
    }

    private void testCase2(String browserName) throws Exception{
        WebDriver driver=initializeDriver(browserName);
        driver.get("https://www.seleniumhq.org/");
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        String title = driver.getTitle();
        System.out.println("Page title is: " + title);
        WebElement element = driver.findElement(By.cssSelector("img[alt='Selenium Grid Logo']"));
        element.click();
        driver = new Augmenter().augment(driver);
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, new File("Screenshot.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}