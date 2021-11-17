package platforms;

import capabilities.GetCapabilities;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import java.net.URL;
import java.util.Map;


public class Android implements PlatformSelectable {
    private static AppiumDriverLocalService service;
    private static AppiumServiceBuilder builder;
    private static AppiumDriver<MobileElement> driver;

    public static void stopServerAfter(String capabilityName) throws Exception {
        builder = new AppiumServiceBuilder();
        builder.withIPAddress("127.0.0.1");
        builder.usingPort(4444);
        builder.withCapabilities(GetCapabilities.getDesiredCapabilities(capabilityName));
        builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
        builder.withArgument(GeneralServerFlag.LOG_LEVEL, "error");
        service = AppiumDriverLocalService.buildService(builder);
        driver.quit();
        service.stop();
    }

    public AppiumDriver<MobileElement> getDriver(String capabilityName) throws Exception {

        for (Map.Entry<String, Object> entry : GetCapabilities.getDesiredCapabilities(capabilityName).asMap().entrySet()) {
            System.out.println("Key = " + entry.getKey() +
                    ", Value = " + entry.getValue());
        }
        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4444/wd/hub"), GetCapabilities.getDesiredCapabilities(capabilityName));
        return driver;
    }
}


