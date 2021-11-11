package platforms;

import capabilities.GetCapabilities;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import java.net.URL;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class Android implements PlatformSelectable {


    public AppiumDriver<MobileElement> getDriver(String capabilityName) throws Exception {

        for (Map.Entry<String, Object> entry : GetCapabilities.getDesiredCapabilities(capabilityName).asMap().entrySet()) {
            System.out.println("Key = " + entry.getKey() +
                    ", Value = " + entry.getValue());
        }
        AppiumDriver<MobileElement> driver = new AndroidDriver<>(new URL("http://127.0.0.1:4444/wd/hub"), GetCapabilities.getDesiredCapabilities(capabilityName));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return driver;
    }
}


