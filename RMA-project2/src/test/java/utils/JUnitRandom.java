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

public class JUnitRandom {
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

    private int resetCounter(int counter, int browserCounter){
        int returnCounter=0;
        if(counter==browserCounter) returnCounter=0;
        else returnCounter = counter;
        return returnCounter;
    }
}