package platforms;

import capabilities.GetCapabilities;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import java.net.URL;



public class Android implements PlatformSelectable {

    private GetCapabilities getCapabilities;
    public AppiumDriver getDriver() throws Exception {
        String localIp = "127.0.0.1";
        String portNo = "4444";
        //kendisi otomatik olarak indiriyor exe olayi icinde oluyor
        AppiumDriver driver = new AndroidDriver(new URL(String.format("http://%s:%s/wd/hub", localIp, portNo)), getCapabilities.getDesiredCapabilities("Pixel_2_toInstall"));
        return driver;
    }
}

