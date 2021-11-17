package helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SendKeysHelper {
    private FindHelper findHelper = new FindHelper();


    public void sendkeys(By by, CharSequence charSequence) {
        findHelper.findElementWithPresenceWait(by);
        findHelper.findElementWitVisibleWait(by);
        WebElement element = findHelper.findElementWitClickableWait(by);
        element.sendKeys(charSequence);
    }

    public void sendKeysWithAction(By by, CharSequence value) {
        TouchActionsHelper touchActionsHelper = new TouchActionsHelper();
        touchActionsHelper.getAction().sendKeys(value).build().perform();
    }

    public void hideKeyboard() {
        DeviceActionsHelper deviceActionsHelper = new DeviceActionsHelper();
        deviceActionsHelper.hideKeyboard();

    }

    //keys such as CANCEL ENTER DELETE
    public void sendKeyToKeyboard(String key) {
        DeviceActionsHelper deviceActionsHelper = new DeviceActionsHelper();
        deviceActionsHelper.sendKeyToKeyboard(key);
    }

    //keys hello look at
    public void sendKeysToKeyboard(String keys) {
        DeviceActionsHelper deviceActionsHelper = new DeviceActionsHelper();
        deviceActionsHelper.sendKeysToKeyboard(keys);
    }

    //    DEL ,E, ENTER
    public void pressKeyAndroid(String pressKey) {
        DeviceActionsHelper deviceActionsHelper = new DeviceActionsHelper();
        deviceActionsHelper.pressKeyAndroid(pressKey);

    }

    public void clearText(By by) {
        findHelper.findElementWithPresenceWait(by).clear();
    }


}
