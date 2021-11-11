package platforms;

import capabilities.GetCapabilities;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;

import java.net.URL;
import java.util.concurrent.TimeUnit;


public class IOS implements PlatformSelectable {


    public AppiumDriver<MobileElement> getDriver(String capabilityName) throws Exception {

        AppiumDriver<MobileElement> driver = new IOSDriver<>(new URL("http://127.0.0.1:4444/wd/hub"), GetCapabilities.getDesiredCapabilities(capabilityName));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return driver;

    }
}
