package helper;

import io.appium.java_client.MobileBy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AndroidUiHelper {
    private final Logger log = LogManager.getLogger(AndroidUiHelper.class);
    private FindHelper findHelper = new FindHelper();

    public WebElement androidUISelectorChecked(String resourceIdd, String booleanValue, String attribute) {

        By by = MobileBy.AndroidUIAutomator("UiSelector().resourceId(\"" + resourceIdd + "\")." + attribute + "(" + booleanValue + ")");
        return findHelper.findElementWithPresenceWait(by);
    }
}
