package helper;

import driver.DriverManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.ios.IOSDriver;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.RemoteKeyboard;

import java.io.File;
import java.security.SecureRandom;
import java.time.Duration;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class DeviceActionsHelper {

    private final Logger log = LogManager.getLogger(DeviceActionsHelper.class);

    public AppiumDriver getDriver() {
        return DriverManager.getInstances().getDriver();
    }

    public IOSDriver getIOSDriver() {
        return (IOSDriver) DriverManager.getInstances().getDriver();
    }

    public AndroidDriver getAndroidDriver() {
        return (AndroidDriver) DriverManager.getInstances().getDriver();
    }

    // TODO: 12.11.2021 burda try catch yapmalimiydik ayria fail oldugunda ss koyma olayini nereye koymaliyiz
    public void getScreenshot(String packageNameOrBundleId) throws Exception {
        File srcFile = getDriver().getScreenshotAs(OutputType.FILE);
        SecureRandom random = new SecureRandom();
        String filename = UUID.randomUUID().toString();
        File targetFile = new File(System.getProperty("user.dir"), "src/test/resources/ss" + filename + new Date().toString() + ".jpg");
        FileUtils.copyFile(srcFile, targetFile);
    }

    private RemoteKeyboard getKeyboard() {
        return (RemoteKeyboard) (getDriver()).getKeyboard();

    }

    public void hideKeyboard() {
        (getDriver()).hideKeyboard();

    }

    //keys such as CANCEL ENTER DELETE
    public void sendKeyToKeyboard(String key) {

        getKeyboard().sendKeys(Keys.valueOf(key));
    }

    //keys hello look at
    public void sendKeysToKeyboard(String keys) {

        getKeyboard().sendKeys(keys);
    }

    //press key code!!!!
    public void pressKeyAndroid(String pressKey) {
        AndroidKey key = AndroidKey.valueOf(pressKey);
        ((AndroidDriver) getDriver()).pressKey(new KeyEvent(key));

    }

    public String getOrientation() {
        return getDriver().getOrientation().value();
    }

    // TODO: 12.11.2021 try catch konmalimi
    public void setOrientation(String orientation) {
        if (getDriver() != null) {
            switch (orientation.toUpperCase()) {
                case "LANDSCAPE":
                    getDriver().rotate(ScreenOrientation.LANDSCAPE);
                    break;
                case "PORTRAIT":
                    getDriver().rotate(ScreenOrientation.PORTRAIT);
                    break;
                default:
                    getDriver().rotate(ScreenOrientation.PORTRAIT);
                    break;
            }
        }
    }

    public String getDeviceTime() {
        return getDriver().getDeviceTime();
    }

    public void shakeDevice() {
        getIOSDriver().shake();
    }

    public String getBatteryState() {
        if (getDriver().getAutomationName().equalsIgnoreCase("XCUiTest")) {
            return getIOSDriver().getBatteryInfo().getState().toString();
        } else {
            return getAndroidDriver().getBatteryInfo().getState().toString();
        }
    }

    public String getBatteryInfo() {
        if (Objects.requireNonNull(getDriver().getAutomationName()).equalsIgnoreCase("XCUiTest")) {
            return getIOSDriver().getBatteryInfo().toString();
        } else {
            return getAndroidDriver().getBatteryInfo().toString();
        }
    }

    public void LockDevice() {
        if (Objects.requireNonNull(getDriver().getAutomationName()).equalsIgnoreCase("XCUiTest")) {
            getIOSDriver().lockDevice();
        } else {
            getAndroidDriver().lockDevice();
        }
    }

    public void LockDevice(int timeout) {
        if (Objects.requireNonNull(getDriver().getAutomationName()).equalsIgnoreCase("XCUiTest")) {
            getIOSDriver().lockDevice(Duration.ofSeconds(timeout));
        } else {
            getAndroidDriver().lockDevice(Duration.ofSeconds(timeout));
        }
    }

    public void unLockDevice() {
        if (Objects.requireNonNull(getDriver().getAutomationName()).equalsIgnoreCase("XCUiTest")) {
            getIOSDriver().unlockDevice();
        } else {
            getAndroidDriver().unlockDevice();
        }
    }

    public boolean checkDeviceLocked() {
        if (Objects.requireNonNull(getDriver().getAutomationName()).equalsIgnoreCase("XCUiTest")) {
            return getIOSDriver().isDeviceLocked();
        } else {
            return getAndroidDriver().isDeviceLocked();
        }
    }

    public boolean checkIfKeyboardShown() {
        if (Objects.requireNonNull(getDriver().getAutomationName()).equalsIgnoreCase("XCUiTest")) {
            return getIOSDriver().isKeyboardShown();
        } else {
            return getAndroidDriver().isKeyboardShown();
        }
    }

    public void failTouchId() {
        if (Objects.requireNonNull(getDriver().getAutomationName()).equalsIgnoreCase("XCUiTest")) {
            getIOSDriver().toggleTouchIDEnrollment(true);
            getIOSDriver().performTouchID(false);
        }
    }

    public void PassTouchId() {
        if (Objects.requireNonNull(getDriver().getAutomationName()).equalsIgnoreCase("XCUiTest")) {
            getIOSDriver().toggleTouchIDEnrollment(true);
            getIOSDriver().performTouchID(true);
        }
    }
//
}



