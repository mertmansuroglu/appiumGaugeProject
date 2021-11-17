package helper;

import io.appium.java_client.MobileElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ElementActionsHelper {

    private final Logger log = LogManager.getLogger(ElementActionsHelper.class);
    private FindHelper findHelper = new FindHelper();

    // TODO: 12.11.2021 asagidakilerden hangisine try catch koyabiliriz!!!

    public String getElementAttribute(By by, String attributeName) {
        findHelper.findElementWithPresenceWait(by);
        findHelper.findElementWitVisibleWait(by);
        WebElement element = findHelper.findElementWitClickableWait(by);
        log.info("'{}' objesininin. {} attribute naminin valuesi {} dir", by, attributeName, element.getAttribute(attributeName));
        return element.getAttribute(attributeName);
    }

    public int getElementWidth(By by, String attributeName) {
        findHelper.findElementWithPresenceWait(by);
        findHelper.findElementWitVisibleWait(by);
        MobileElement element = (MobileElement) findHelper.findElementWitClickableWait(by);
        log.info("'{}' objesininin. width valuesi {} dir", by, element.getLocation().x);
        return element.getCenter().x;
    }

    public int getElementHeight(By by, String attributeName) {
        findHelper.findElementWithPresenceWait(by);
        findHelper.findElementWitVisibleWait(by);
        MobileElement element = (MobileElement) findHelper.findElementWitClickableWait(by);
        log.info("'{}' objesininin.height valuesi {} dir", by, element.getLocation().y);
        return element.getCenter().y;
    }

    public int getElementlistWidth(By by, String index) {
        findHelper.findElementsWithVisibleWait(by);
        List<WebElement> element = findHelper.findElementsWithPresenceWait(by);
        return element.get(Integer.parseInt(index)).getLocation().x;
    }

    public int getListSize(By by) {
        findHelper.findElementsWithVisibleWait(by);
        List<WebElement> element = findHelper.findElementsWithPresenceWait(by);
        return element.size();
    }

    public String getLisItemText(By by, int index) {
        findHelper.findElementsWithVisibleWait(by);
        List<WebElement> element = findHelper.findElementsWithPresenceWait(by);
        return element.get(index).getText();
    }

    public String getLisItemAttribute(By by, int index, String attribute) {
        findHelper.findElementsWithVisibleWait(by);
        List<WebElement> element = findHelper.findElementsWithPresenceWait(by);
        return element.get(index).getAttribute(attribute);
    }

    public int getElementlistHeight(By by, String index) {
        findHelper.findElementsWithVisibleWait(by);
        List<WebElement> element = findHelper.findElementsWithPresenceWait(by);
        return element.get(Integer.parseInt(index)).getLocation().y;
    }

    public String getElementText(By by) {
        findHelper.findElementWithPresenceWait(by);
        findHelper.findElementWitVisibleWait(by);
        WebElement element = findHelper.findElementWitClickableWait(by);
        log.info("'{}' objesininin.texti {} dir", by, element.getText());
        return element.getText();
    }

    public void moveElementByPoint(By by, int x, int y) {
        findHelper.findElementWithPresenceWait(by);
        findHelper.findElementWitVisibleWait(by);
        MobileElement element = (MobileElement) findHelper.findElementWitClickableWait(by);
        log.info("'{}' objesininin.texti {} dir", by, element.getText());
        element.getCenter().move(x, y);

    }

    public void moveElementToOtherElement(By by) {
        findHelper.findElementWithPresenceWait(by);
        findHelper.findElementWitVisibleWait(by);
        MobileElement element = (MobileElement) findHelper.findElementWitClickableWait(by);
        MobileElement elementTarget = (MobileElement) findHelper.findElementWitClickableWait(by);
        int x = elementTarget.getCenter().x;
        int y = elementTarget.getCenter().y;
        log.info("'{}' objesininin.texti {} dir", by, element.getText());
        element.getCenter().move(x, y);
    }

    public void getCenter(By by) {
        findHelper.findElementWithPresenceWait(by);
        findHelper.findElementWitVisibleWait(by);
        MobileElement element = (MobileElement) findHelper.findElementWitClickableWait(by);
        MobileElement elementTarget = (MobileElement) findHelper.findElementWitClickableWait(by);
        log.info("'{}' objesininin.texti {} dir", by, element.getText());
        element.getCenter();
    }


}
