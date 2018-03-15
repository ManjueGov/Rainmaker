package utils;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import gherkin.deps.com.google.gson.JsonArray;
import gherkin.deps.com.google.gson.JsonObject;
import gherkin.deps.com.google.gson.JsonParser;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.io.*;
import java.net.URL;

public class DriverFactory
{
    public static RemoteWebDriver driver;
    public static DesiredCapabilities capabilities;
    private String json;
    private String browserName;
    private String platform;
    private String version;

    @Before
    public void setUp() throws IOException {
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
        JsonArray myArray = myObject.get("capabilities").getAsJsonArray();
        JsonObject jsonObject;

        capabilities = DesiredCapabilities.chrome();
        for (int i=0; i<myArray.size(); i++){
            jsonObject = myArray.get(i).getAsJsonObject();
            browserName = jsonObject.get("browserName").getAsString();
            capabilities.setCapability("browserName", browserName);
        }
        driver = new RemoteWebDriver(new URL("http://172.16.2.178:4444/wd/hub"), capabilities);
    }

    @After
    public void tearDown()
    {
        driver.close();
    }

}
