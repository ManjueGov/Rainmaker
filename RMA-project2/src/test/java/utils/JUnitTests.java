package utils;

import gherkin.deps.com.google.gson.JsonArray;
import gherkin.deps.com.google.gson.JsonObject;
import gherkin.deps.com.google.gson.JsonParser;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.*;
import java.net.URL;
import java.util.LinkedList;

@RunWith(utils.Parallelized.class)
public class JUnitTests {
    private String platform;
    private String browserName;
    private String browserVersion;
    private static String json;
    private static JsonArray myArray;

    @Parameterized.Parameters
    public static LinkedList<String[]> getEnvironments() throws Exception {
        LinkedList<String[]> environments = new LinkedList<String[]>();
        FileInputStream fin = new FileInputStream(new File(System.getProperty("user.dir")+"//src//test//resources//testConfig.json"));
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
        JsonArray config = myObject.getAsJsonArray("config");

        for(int i=0; i<config.size(); i++)
        {
            JsonObject pObj = (JsonObject) config.get(i);
            System.out.println(pObj.toString());
            if(pObj.toString().contains("parallel")){
                System.out.println("its parallel "+pObj);
                environments = executeParallel(pObj.getAsJsonArray("parallel"));
            }
            if(pObj.toString().contains("random")){
                System.out.println("its random "+pObj);
                //executeParallel(pObj.getAsJsonArray("random"));
            }
            if(pObj.toString().contains("sequence")){
                System.out.println("its sequence "+pObj);
                // config.get(i).getAsJsonObject("parallel");
                //executeParallel(pObj.getAsJsonArray("sequence"));
            }
        }
        return environments;
    }

    private static LinkedList<String[]> executeParallel(JsonArray config){
        LinkedList<String[]> env = new LinkedList<String[]>();
        JsonArray browserList = config.getAsJsonArray();
        for(int i=0; i<browserList.size();i++){
            env.add(new String[]{Platform.WINDOWS.toString(), browserList.get(i).getAsJsonObject().get("browserName").toString(),"65"});
        }
        return env;
    }

    public JUnitTests(String platform, String browserName, String browserVersion) {
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

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}