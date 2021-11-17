package platforms;

import capabilities.GetCapabilities;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;


public class IOS implements PlatformSelectable {
    private static AppiumDriverLocalService service;
    private static AppiumServiceBuilder builder;
    private static AppiumDriver<MobileElement> driver;
    private final Logger log = LogManager.getLogger(IOS.class);

    //bunu yapmamin sebbei serverin tam olarka kill olmasini istemem!!!
    //hali hazirda olani bulup stop ediyor
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

        driver = new IOSDriver<>(new URL("http://127.0.0.1:4444/wd/hub"), GetCapabilities.getDesiredCapabilities(capabilityName));
        return driver;

    }

    // TODO: 14.11.2021 burayi konus 
    public boolean CheckIfPortServerInUse(int port) throws Exception {
        boolean isServerRunning = false;
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.close();
        } catch (IOException e) {
            //If control comes here, then it means that the port is in use
            isServerRunning = true;
            log.error("{}, port is alredy in use and the value is {},", port, isServerRunning);
        } finally {
            serverSocket = null;
        }
        return isServerRunning;
    }


}
