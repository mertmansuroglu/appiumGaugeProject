package base;

import com.thoughtworks.gauge.AfterScenario;
import driver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseTest {

    private final Logger log = LogManager.getLogger(BaseTest.class);

    //burda ayaga kaldirildimi onu test ederiz
    //helperlarda direk drivermanager get driver i kullanacaz!!
    public void setUp(String platformType, String capabilityName) throws Exception {
        DriverManager.getInstances().createLocalDriver(platformType, capabilityName);
        log.info("appium ayağa kaldırıldı.");
    }

    @AfterScenario
    public void tearDown() {
        DriverManager.getInstances().quitDriver();
    }
}
