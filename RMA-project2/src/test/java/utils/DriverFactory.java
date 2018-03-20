package utils;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory
{
    private RemoteWebDriver driver;
    private DesiredCapabilities capabilities;
    private String json;
    private String browserName;

    public RemoteWebDriver init(String browserName) {
         this.browserName = browserName;
        return createNewDriverInstance();
    }

    private RemoteWebDriver createNewDriverInstance() {
        capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", browserName);
        try {
            driver = new RemoteWebDriver(new URL("http://172.16.2.178:4444/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;
    }
    public void destroy()
    {
        driver.close();
    }
    /*@Before
    public void setUp() throws IOException {
       *//* //with json
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
        JsonArray myArray = myObject.getAsJsonArray("capabilities");
        JsonObject jsonObject;

        capabilities = new DesiredCapabilities();
        for (int i=0; i<myArray.size(); i++){
            browserName = myArray.get(i).getAsJsonObject().get("browserName").getAsString();
            capabilities.setCapability("browserName", browserName);

            //driver.close();
        }
        capabilities.setCapability("browserName", browserName);
        driver = new RemoteWebDriver(new URL("http://172.16.2.178:4444/wd/hub"), capabilities);*//*

        capabilities.setCapability("browserName", browserName);
        try {
            driver = new RemoteWebDriver(new URL("http://172.16.2.178:4444/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    @After
    public void tearDown()
    {
        driver.close();
    }*/

}