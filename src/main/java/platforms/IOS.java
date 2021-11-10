package platforms;

import capabilities.GetCapabilities;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;

import java.net.URL;


public class IOS implements PlatformSelectable {

    private GetCapabilities getCapabilities;

    public AppiumDriver<MobileElement> getDriver() throws Exception {
        //todo burada normal spec veya feature yazılırken "iPhone_13_toInstall","localIp",""portNo" bu bilgileri
        // nereden alacaksın?
        // buraya hardcoded eklersen dışarıdan alamassın.
        String localIp = "127.0.0.1";
        String portNo = "4444";

        return (AppiumDriver<MobileElement>) new IOSDriver(new URL(String.format("http://%s:%s/wd/hub", localIp, portNo)),
                getCapabilities.getDesiredCapabilities("iPhone_13_toInstall"));
    }
}
