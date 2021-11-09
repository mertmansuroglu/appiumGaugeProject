package base;

import com.thoughtworks.gauge.AfterScenario;
import driver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseDriver {

    private final Logger log = LogManager.getLogger(BaseDriver.class);


    public void setUp() throws Exception {
        DriverManager.getInstances().createLocalDriver();
        log.info("appium ayağa kaldırıldı.");
    }

    @AfterScenario
    public void tearDown() {
        DriverManager.getInstances().quitDriver();
    }
}
