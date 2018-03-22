import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class FirstAppiumTest {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("deviceName", "172.16.2.131:5555");
        cap.setCapability("platForm", "Andriod");
        cap.setCapability("platFormVersion", "5.0.1");
//        cap.setCapability("app", "E:\\eGov-Automation\\andriodAuto\\demo\\src\\main\\app\\flipkart.apk");
        cap.setBrowserName("Chrome");
        cap.getBrowserName();

        AndroidDriver driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), cap);
        driver.get("http://egov-micro-dev.egovernments.org/app/v1/#/default");
        driver.findElement(By.id("username")).sendKeys("murali");
        driver.findElement(By.id("password")).sendKeys("12345678");
        driver.findElement(By.xpath(".//div/span[text()='Sign In']")).click();
        Thread.sleep(200);
        driver.findElement(By.xpath("//i[.='more_vert']")).click();
        driver.findElement(By.xpath("//i[.='lock']")).click();
//        List<MobileElement> button = driver.findElements(By.className("android.widget.Button"));
//        button.get(0).click();
        driver.quit();
    }
}