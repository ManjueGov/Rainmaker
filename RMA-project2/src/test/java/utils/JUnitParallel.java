package utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

@RunWith(utils.Parallelized.class)
public class JUnitParallel {
    private String platform;
    private String browserName;
    private String browserVersion;

    @Parameterized.Parameters
    public static LinkedList<String[]> getEnvironments() throws Exception {
        LinkedList<String[]> env = new LinkedList<String[]>();
        env.add(new String[]{Platform.WINDOWS.toString(), "chrome","65"});
        env.add(new String[]{Platform.WINDOWS.toString(),"chrome","65"});
        env.add(new String[]{Platform.WINDOWS.toString(),"chrome","65"});

        //add more browsers here

        return env;
    }


    public JUnitParallel(String platform, String browserName, String browserVersion) {
        this.platform = platform;
        this.browserName = browserName;
        this.browserVersion = browserVersion;
    }

    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capability = new DesiredCapabilities();
        capability.setCapability("browser", browserName);
//        capability.setCapability("name", "Parallel test");
        driver = new RemoteWebDriver(new URL("http://172.16.2.178:4444/wd/hub"), DesiredCapabilities.chrome());
    }

    @Test
    public void testSimple() throws Exception {
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
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}