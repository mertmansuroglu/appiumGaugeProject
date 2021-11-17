package base;

import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.AfterStep;
import com.thoughtworks.gauge.Gauge;
import driver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import platforms.Android;
import platforms.IOS;

public class BaseTest {

    private final Logger log = LogManager.getLogger(BaseTest.class);
    IOS ios = new IOS();

    @AfterScenario
    public static void tearDown(String capabilityName) throws Exception {

        if (DriverManager.getInstances().getDriver().getClass().getSimpleName().equalsIgnoreCase("AndroidDriver")) {
            Android.stopServerAfter(capabilityName);
        } else
            IOS.stopServerAfter(capabilityName);
    }

    @AfterStep
    public static void getScreenShot(String capabilityName) throws Exception {

        Gauge.captureScreenshot();
    }

    //burda ayaga kaldirildimi onu test ederiz
    //helperlarda direk drivermanager get driver i kullanacaz!!
    public void setUp(String platformType, String capabilityName) throws Exception {
        DriverManager.getInstances().createLocalDriver(platformType, capabilityName);
        log.info("appium ayağa kaldırıldı.");
    }
}
