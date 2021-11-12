package helper;

import driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FindHelper {
    private PresenceWaitHelper presenceHelper = new PresenceWaitHelper();
    private ClickableWaitHelper clickableHelper = new ClickableWaitHelper();
    private VisibleWaitHelper visibleHelper = new VisibleWaitHelper();
    private TextWaitHelper textWaitHelper = new TextWaitHelper();


//    private GetLocator getLocator = new GetLocator();


//    public WebElement findElementWithPresenceWait(String filePath,String keyword) throws NoSuchSelector {
//        var by= getLocator.getElementBy(filePath,keyword);
//       return presenceHelper.waitUntilPresence(by);
//    }

    public WebElement findElementWithPresenceWait(By by) {
        return presenceHelper.waitUntilPresence(by);
    }

    public WebElement findElementWithPresenceWait(By by, int timeout) {
        return presenceHelper.waitUntilPresence(by, timeout);
    }

    public List<WebElement> findElementsWithPresenceWait(By by) {
        return presenceHelper.waitUntilPresenceAll(by);
    }

    public List<WebElement> findElementsWithPresenceWait(By by, int timeout) {
        return presenceHelper.waitUntilPresenceAll(by, timeout);
    }

    public WebElement findElementWithPresencePolling(By by, int pollingDuration) {
        return presenceHelper.waitUntilPresencePolling(by, pollingDuration);
    }

    public WebElement findElementWithPresencePolling(By by, int timeout, int pollingDuration) {
        return presenceHelper.waitUntilPresencePolling(by, timeout, pollingDuration);
    }

    public List<WebElement> findElementsWithPresencePolling(By by, int timeout, int pollingDuration) {
        return presenceHelper.waitUntilPresenceAllPolling(by, timeout, pollingDuration);
    }

    public List<WebElement> findElementsWithPresencePolling(By by, int pollingDuration) {
        return presenceHelper.waitUntilPresenceAllPolling(by, pollingDuration);
    }


    public WebElement findElementWitClickableWait(By by) {
        return clickableHelper.waitUntilClickable(by);
    }

    public WebElement findElementWitClickableWait(By by, int timeout) {
        return clickableHelper.waitUntilClickable(by, timeout);
    }

    public WebElement findElementWitClickableWaitPolling(By by, int pollingDuration) {
        return clickableHelper.waitUntilClickable(by, pollingDuration);
    }

    public WebElement findElementWitClickableWaitPolling(By by, int timeout, int pollingDuration) {
        return clickableHelper.waitUntilClickablePolling(by, timeout, pollingDuration);
    }

    public WebElement findElementWitVisibleWait(By by) {
        return visibleHelper.waitUntilVisible(by);
    }


    public WebElement findElementWitVisibleWait(By by, int timeout) {
        return visibleHelper.waitUntilVisible(by, timeout);
    }


    public WebElement findElementWithVisiblePolling(By by, int pollingDuration) {
        return visibleHelper.waitUntilVisiblePolling(by, pollingDuration);
    }

    public WebElement findElementWithVisiblePolling(By by, int timeout, int pollingDuration) {
        return visibleHelper.waitUntilVisiblePolling(by, timeout, pollingDuration);
    }


    public List<WebElement> findElementsWithVisibleWait(By by, int timeout) {
        return visibleHelper.waitUntilVisibleAll(by, timeout);
    }

    public List<WebElement> findElementsWithVisibleWait(By by) {
        return visibleHelper.waitUntilVisibleAll(by);
    }

    public List<WebElement> findElementsWithVisiblePolling(By by, int timeout, int pollingDuration) {
        return visibleHelper.waitUntilVisibleAllPolling(by, timeout, pollingDuration);
    }

    public List<WebElement> findElementsWithVisiblePolling(By by, int pollingDuration) {
        return visibleHelper.waitUntilVisibleAllPolling(by, pollingDuration);
    }

    //burasi ikinci kisim yani driveri get ettigimiz yer
    public WebElement findElement(By by) {
        return DriverManager.getInstances().getDriver().findElement(by);
    }

    public List<WebElement> findElements(By by) {
        return DriverManager.getInstances().getDriver().findElements(by);
    }


}
