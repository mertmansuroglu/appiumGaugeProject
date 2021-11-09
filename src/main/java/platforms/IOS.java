package platforms;

import capabilities.GetCapabilities;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;

import java.net.URL;


public class IOS implements PlatformSelectable {

    private GetCapabilities getCapabilities;

    public AppiumDriver getDriver() throws Exception {
        String localIp = "127.0.0.1";
        String portNo = "4444";
        //kendisi otomatik olarak indiriyor exe olayi icinde oluyor
        AppiumDriver driver = new IOSDriver(new URL(String.format("http://%s:%s/wd/hub", localIp, portNo)), getCapabilities.getDesiredCapabilities("iPhone_13_toInstall"));
        return driver;
    }
}
