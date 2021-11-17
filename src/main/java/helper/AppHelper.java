package helper;

import driver.DriverManager;
import io.appium.java_client.AppiumDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchSessionException;

import java.time.Duration;

public class AppHelper {
    private final Logger log = LogManager.getLogger(AppHelper.class);

    public AppiumDriver getDriver() {
        return DriverManager.getInstances().getDriver();
    }

    // TODO: 12.11.2021 burda attigimiz throw dogrumu ??? 
    public void terminateApp(String packageNameOrBundleId) throws Exception {
        if (getDriver() != null && Boolean.TRUE.equals(isAppInstalled(packageNameOrBundleId))) {
            try {
                getDriver().terminateApp(packageNameOrBundleId);
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("bundle Id or App package should be defined correctly");
            }
        }
    }

    public void switchApp(String packageNameOrBundleId) throws Exception {
        if (getDriver() != null && Boolean.TRUE.equals(isAppInstalled(packageNameOrBundleId))) {
            try {
                getDriver().activateApp(packageNameOrBundleId);
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("bundle Id or App package should be defined correctly");
            }

        }
    }

    public void resetApp() throws Exception {
        if (getDriver() != null) {
            try {
                getDriver().resetApp();
            } catch (Exception e) {
                e.printStackTrace();
                throw new NoSuchSessionException();
            }
        }
    }

    public void closeApp() throws Exception {
        if (getDriver() != null) {
            try {
                getDriver().closeApp();
            } catch (Exception e) {
                e.printStackTrace();
                throw new NoSuchSessionException();
            }
        }
    }

    public void relaunchApp() throws Exception {
        if (getDriver() != null) {
            try {
                getDriver().launchApp();
            } catch (Exception e) {
                e.printStackTrace();
                throw new NoSuchSessionException();
            }
        }
    }

    // TODO: 12.11.2021 boolean donuyor void yaptim mantikli mi ordaki boolean ne demek istiyor
    public void removeApp(String packageNameOrBundleId) throws Exception {
        if (getDriver() != null && Boolean.TRUE.equals(isAppInstalled(packageNameOrBundleId))) {

            try {
                getDriver().removeApp(packageNameOrBundleId);
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("bundle Id or App package should be defined correctly");
            }
        }
    }

    public void installApp(String packageNameOrBundleId) throws Exception {
        if (getDriver() != null) {
            try {
                getDriver().installApp(packageNameOrBundleId);
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("bundle Id or App package should be defined correctly");
            }
        }
    }

    // TODO: 12.11.2021 burdaki mantik dogrumu 
    public Boolean isAppInstalled(String packageNameOrBundleId) {
        if (getDriver() != null) {
            boolean appExist = getDriver().isAppInstalled(packageNameOrBundleId);
            if (appExist) {
                return true;
            }
        }
        log.info("app was not installed in the device");
        return false;
    }


    public void runAppBackGround(int seconds) {
        getDriver().runAppInBackground(Duration.ofSeconds(seconds));
    }

    public void runAppBackGroundWithoutStop() {
        runAppBackGround(-1);
    }
}
