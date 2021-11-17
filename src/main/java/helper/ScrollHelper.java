package helper;

import exceptions.NoSuchSelector;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import locators.GetLocator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import java.util.HashMap;

public class ScrollHelper {

    private final Logger log = LogManager.getLogger(ScrollHelper.class);
    private FindHelper findHelper = new FindHelper();
    // TODO: 12.11.2021 asagidakilerden hangisine try catch koyabiliriz!!!


    public void scrollToTextContains(String text) throws NoSuchSelector {
        By by = MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(" + "new UiSelector().textContains(\"" + text + "\"));");
        findHelper.findElement(by);
        findHelper.findElementWithVisiblePolling(GetLocator.generateByElement("TEXT_CONTAINS", text));
        log.info("'{}' text icerenine scroll edildi", text);
    }

    public void scrollToText(String text) throws NoSuchSelector {
        By by = MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"" + text + "\").instance(0))");
        findHelper.findElement(by);
        findHelper.findElementWitVisibleWait(GetLocator.generateByElement("TEXT_EQUALS", text));
        log.info("'{}' textine scroll edildi", text);
    }

    public void scrollByID(String id, int index) throws NoSuchSelector {
        try {
            By by = MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().resourceId(\"" + id + "\").instance(" + index + "));");
            findHelper.findElement(by);
            findHelper.findElementWitVisibleWait(GetLocator.generateByElement("id", id));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void scrollWithElementAndroid(int width, int height, int left, int top, String direction, double percent, Integer speed) {
        try {

            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("width", width);
            param.put("height", height);
            param.put("left", left);
            param.put("top", top);
            param.put("direction", direction);
            param.put("percent", percent);
            param.put("speed", speed);
            JavascriptHelper j = new JavascriptHelper();
            j.executeJavascript("mobile: scrollGesture", param);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void androidScrollUntil(By scrollableObject, By targetObject, double percent, int speed, String direction) throws InterruptedException {
        ScrollHelper scrollHelper = new ScrollHelper();
        SwipeHelper swipeHelper = new SwipeHelper();
        TouchActionsHelper touchActionsHelper = new TouchActionsHelper();
        MobileElement element = (MobileElement) findHelper.findElementWitClickableWait(scrollableObject);
        boolean isDisplayed = false;
        int width = element.getCenter().x;
        int height = element.getCenter().y;
        int left = element.getCenter().getX();
        int top = element.getCenter().getY();
        if ((findHelper.findElements(targetObject).size() == 1)) {
            swipeHelper.actionSwipeBetweenTwoElement(scrollableObject, targetObject);
        }

        int i = 0;
        do {
            scrollWithElementAndroid(width, height, left, top, direction, percent, speed);
            i++;
            if (i == 15) {
                break;
            }
            try {
                isDisplayed = findHelper.findElement(targetObject).isDisplayed();
            } catch (Exception e) {
                isDisplayed = false;
            }
        } while (!isDisplayed);
    }

    public void scrollIOS(String direction) {
//biz type i ni kullaniciya biraktik ister text olur ister value olur.directionida kullaniciya biraktik

        try {
            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("direction", direction);
            JavascriptHelper j = new JavascriptHelper();
            j.executeJavascript("mobile: scroll", param);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void iosScrollUntil(By sourceObject, By targetObject, String direction) throws InterruptedException {
        SwipeHelper swipeHelper = new SwipeHelper();
        boolean isDisplayed = false;
        if ((findHelper.findElements(targetObject).size() == 1)) {
            swipeHelper.actionSwipeBetweenTwoElement(sourceObject, targetObject);
        }
        scrollIOS(direction);
        int i = 0;
        do {
            scrollIOS(direction);
            i++;
            if (i == 15) {
                break;
            }
            try {
                isDisplayed = findHelper.findElement(targetObject).isDisplayed();
            } catch (Exception e) {
                isDisplayed = false;
            }
        } while (!isDisplayed);
    }

    public void iosScrollUntilJS(By sourceObject, By targetObject, String direction) throws InterruptedException {
//biz type i ni kullaniciya biraktik ister text olur ister value olur.directionida kullaniciya biraktik
        SwipeHelper swipeHelper = new SwipeHelper();
        if ((findHelper.findElements(targetObject).size() == 1)) {
            swipeHelper.actionSwipeBetweenTwoElement(sourceObject, targetObject);
        }
        WebElement element = findHelper.findElement(targetObject);
        try {
            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("direction", direction);
            param.put("element", ((RemoteWebElement) element).getId());
            JavascriptHelper j = new JavascriptHelper();
            j.executeJavascript("mobile: swipe", param);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

