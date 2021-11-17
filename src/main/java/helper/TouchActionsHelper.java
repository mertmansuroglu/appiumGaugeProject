package helper;

import driver.DriverManager;
import io.appium.java_client.MobileElement;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.List;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;


public class TouchActionsHelper {
    private final Logger log = LogManager.getLogger(TouchActionsHelper.class);
    private FindHelper findHelper = new FindHelper();


    public TouchAction getTouchAction() {
        TouchAction action = new TouchAction(DriverManager.getInstances().getDriver());
        return action;

    }

    public Actions getAction() {
        Actions action = new Actions(DriverManager.getInstances().getDriver());
        return action;

    }

    public void handleSeekBarAndroid(By by, double percent) throws InterruptedException {
        WebElement seek_bar = findHelper.findElementWithPresenceWait(by);
        //start coordinate of seekbar
        int start = seek_bar.getLocation().getX();
        //get width seekbar size
        int end = seek_bar.getSize().getWidth();
        //dikey olarak seek bar coordinati
        int y = seek_bar.getLocation().getY();
        int moveToPortion = (int) (end * percent);
        getTouchAction().longPress(PointOption.point(start, y)).moveTo(PointOption.point(moveToPortion, y)).release().perform();
        Thread.sleep(3000);
    }

    public void handleSeekBarIOS(By sliderby, String percent) throws InterruptedException {
        findHelper.findElementsWithPresenceWait(sliderby);
        findHelper.findElementWitClickableWait(sliderby);
        List<MobileElement> slide = findHelper.findElementsMobile(sliderby);
        System.out.println("" + percent + "%");
        slide.get(0).setValue("" + percent + "%");

    }

    public TouchAction actionTap(By by) {
        findHelper.findElementWithPresenceWait(by);
        findHelper.findElementWitVisibleWait(by);
        MobileElement element = (MobileElement) findHelper.findElementWitClickableWait(by);
        return getTouchAction().tap(TapOptions.tapOptions().withElement(ElementOption.element(element)));
    }

    public void actionDoubleTap(By by) {
        findHelper.findElementWithPresenceWait(by);
        findHelper.findElementWitVisibleWait(by);
        MobileElement element = (MobileElement) findHelper.findElementWitClickableWait(by);
        MultiTouchAction multiTouch = new MultiTouchAction(DriverManager.getInstances().getDriver());
        multiTouch.add(actionTap(by))
                .add(actionTap(by))
                .perform();
        log.info("'{}' objesine cift tap edildi.", by);
//        new TouchAction(driver).press(PointOption.point(328, 185)).release().perform().press(PointOption.point(338, 185)).release().perform();
    }


    // tiklayip basili tutar
    public void actionLongPress(By by, int duration) throws InterruptedException {
        findHelper.findElementWithPresenceWait(by);
        findHelper.findElementWitVisibleWait(by);
        WebElement element = findHelper.findElementWitClickableWait(by);
        getTouchAction().longPress(longPressOptions().withElement(element(element)).withDuration(Duration.ofSeconds(duration))).release().perform();
        log.info("'{}' objesine uzun basildi.", by);
    }

    // TODO: 12.11.2021 try catch yapilabilir
    public void touchActiondragDrop(By dragBy, By dropBy, int duration) {
        findHelper.findElementWithPresenceWait(dragBy);
        findHelper.findElementWitVisibleWait(dragBy);
        WebElement element = findHelper.findElementWitClickableWait(dragBy);
        WebElement elementDrop = findHelper.findElementWitClickableWait(dropBy);
        getTouchAction().longPress(longPressOptions().withElement(element(element)).withDuration(ofSeconds(duration))).moveTo(element(elementDrop)).release().perform();
    }

    public void touchActiondragDrop(By dragBy, By dropBy) {
        findHelper.findElementWithPresenceWait(dragBy);
        findHelper.findElementWitVisibleWait(dragBy);
        WebElement element = findHelper.findElementWitClickableWait(dragBy);
        WebElement elementDrop = findHelper.findElementWitClickableWait(dropBy);
        getTouchAction().longPress(longPressOptions().withElement(element(element)).withDuration(ofSeconds(4))).moveTo(element(elementDrop)).release().perform();
    }

    public void actionDragDrop(By sourceElementBy, By targetElementBy) {
        findHelper.findElementWithPresenceWait(sourceElementBy);
        findHelper.findElementWitVisibleWait(sourceElementBy);
        WebElement element = findHelper.findElementWitClickableWait(sourceElementBy);
        WebElement targetElement = findHelper.findElementWitVisibleWait(targetElementBy);
        getAction().dragAndDrop(element, targetElement).build().perform();
        log.info("{}, elementinin üstünde  basılı tutuldu ve {}, elementinin üstüne kadar sürüklenip bırakıldı.", element, targetElement);
    }

    public void actionDragDropBy(By sourceElementBy, int x, int y) {
        findHelper.findElementWithPresenceWait(sourceElementBy);
        findHelper.findElementWitVisibleWait(sourceElementBy);
        WebElement element = findHelper.findElementWitClickableWait(sourceElementBy);
        getAction().dragAndDropBy(element, x, y).build().perform();
        log.info("{}, elementinin üstünde  basılı tutuldu ve {}, {} kordinatlarına kadar sürüklenip bırakıldı.", element, x, y);
    }
}
