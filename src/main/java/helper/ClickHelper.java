package helper;

import io.appium.java_client.touch.offset.PointOption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ClickHelper {

    private final Logger log = LogManager.getLogger(ClickHelper.class);
    private PresenceWaitHelper presenceWaitHelper = new PresenceWaitHelper();
    private FindHelper findHelper = new FindHelper();


    public void clickElement(By by) {
        findHelper.findElementWitVisibleWait(by);
        findHelper.findElementWithPresenceWait(by);
        findHelper.findElementWitClickableWait(by);
        try {
            findHelper.findElement(by).click();
        } catch (Exception e) {
            log.error(e.getMessage());
            log.error("{} was not clicked ", by);
            throw e;
        }
        log.info("'{}' elementine tıklandı.", by);
    }

    public void clickListItemWithIndex(By by, int index) {
        try {
            findHelper.findElementsWithPresenceWait(by).get(index).click();
        } catch (Exception e) {
            log.error(e.getMessage());
            log.error("{} list item was not clicked", by);
            throw e;
        }
        log.info("'{}' liste itemine tıklandı.", by);
    }

    public void clickTheListItemWithText(By by, String text) {
        findHelper.findElementWitClickableWait(by);
        findHelper.findElement(by).click();
        List<WebElement> options = findHelper.findElementsWithPresenceWait(by);
        for (WebElement option : options) {
            if (option.getText().equalsIgnoreCase(text)) {
                option.click();
                break;
            }
        }
    }

    public void clickWithCoordinate(int x, int y) {
        TouchActionsHelper touchActionsHelper = new TouchActionsHelper();
        touchActionsHelper.getTouchAction().tap(PointOption.point(x, y))
                .perform().release();
        log.info("'{}' x ve '{}' y  kordinatina  tıklandı.", x, y);
    }


    public void clickJavaScript(By by) {
        WebElement webElement = findHelper.findElementWithPresenceWait(by);
        String script = "arguments[0].click();";
        new JavascriptHelper().executeJavascript(script, webElement);
    }

    //     * Belirtilen ilk element tıklanabilir değilse ikinci elementi tıklıyor
//     * eğer mevcutsa ilk elementi tıklıyor.
    public void clickSecondElmIfFirstNotExists(By firstBy, By secondBy, int timeout) {
        try {
            presenceWaitHelper.waitUntilPresence(firstBy, timeout);
            clickElement(firstBy);
        } catch (Exception e) {
            presenceWaitHelper.waitUntilPresence(firstBy, timeout);
            clickElement(secondBy);
        }
    }

}


