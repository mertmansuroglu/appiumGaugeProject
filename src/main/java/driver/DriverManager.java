package driver;

import enums.Platforms;
import io.appium.java_client.AppiumDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchSessionException;
import platforms.Android;
import platforms.IOS;
import platforms.PlatformSelectable;

import java.util.Locale;

public class DriverManager {

    // TODO: 12.11.2021 driveri static yaptim dogrumu 
    //we use singleton
//driveri surekli static olarak public olarak tutmak yazilim kurali acisindan yanlis
//bu sebeple onu bir methodla getirmemiz lazim bunun icin kullanilan pattern singleton bu sebeple surekli
//newlemeye gerek olmayacak
    private static DriverManager instances = null;
    private static AppiumDriver driver;
    private final Logger log = LogManager.getLogger(DriverManager.class);
    private PlatformSelectable platformSelectable;

    private DriverManager() {

    }
    //When you call this method, if there is no driver, it create a new driver!
    //get instance daki instance driveri refer eder

    //GET INSTANCE = class newlenmismi init edilmismi ona bakiyor eger edilmediyse yenisini newliyor

    //driveri normalde extend edip class class dolastiracaktik
    public static DriverManager getInstances() {
        if (instances == null) {
            instances = new DriverManager();
        }
        return instances;
    }

    //its about to reading which browser will be used
    //browserName bu resource a eklenen properties filedan hanggi browser oldugunu ogrenmek icindir
    public void createLocalDriver(String platformType, String capabilityName) throws Exception {
        Platforms platformmType = Platforms.valueOf(platformType.toUpperCase(Locale.ROOT));

        switch (platformmType) {
            case IOS:
                platformSelectable = new IOS();
                setDriver(platformSelectable.getDriver(capabilityName));
                break;
            case ANDROID:
                platformSelectable = new Android();
                setDriver(platformSelectable.getDriver(capabilityName));
                break;
            default:
                log.error("you did not put the correct platform name");
                throw new IllegalArgumentException();

        }
    }


    public void quitDriver() {
        try {
            if (getDriver() != null) {
                getDriver().close();
                getDriver().quit();
            }
        } catch (NoSuchSessionException e) {
            log.debug("NoSuchSessionException occurred");
        }
    }

    //
    public AppiumDriver getDriver() {
        return driver;
    }

    public void setDriver(AppiumDriver driver) {
        this.driver = driver;
    }


}
