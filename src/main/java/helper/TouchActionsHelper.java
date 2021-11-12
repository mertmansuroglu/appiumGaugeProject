package helper;

import driver.DriverManager;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;

import java.time.Duration;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;


public class TouchActionsHelper {
    private final Logger log = LogManager.getLogger(TouchActionsHelper.class);
    private FindHelper findHelper = new FindHelper();


    private TouchActions getTouchActions() {
        TouchActions actions = new TouchActions(DriverManager.getInstances().getDriver());
        return actions;

    }

    private TouchAction getTouchAction() {
        TouchAction action = new TouchAction(DriverManager.getInstances().getDriver());
        return action;

    }

    // TODO: 12.11.2021 buralara try catch konabilirmi konursa ne konur
    //biz direk web element gondermedende yapabilirdik ama asagida element gonderip yaptik
    //by i gondermek icin clickable helper icerisine koyup gonderdik
    public void sendKeys(By by, CharSequence value) {
        findHelper.findElementWithPresenceWait(by);
        findHelper.findElementWitVisibleWait(by);
        WebElement element = findHelper.findElementWitClickableWait(by);
        //actions ile yapilacak islemi once build ile execute e ready ederiz sonrada performla execute yapariz
        getTouchActions().sendKeys(element, value).build().perform();
        log.info("'{}' objesine '{}' değeri yazıldı.", by, value);
    }

    public void actionClick(By by) {
        findHelper.findElementWithPresenceWait(by);
        findHelper.findElementWitVisibleWait(by);
        WebElement element = findHelper.findElementWitClickableWait(by);
        getTouchActions().click(element).build().perform();
        log.info("'{}' objesine tıklandı.", by);
    }

    public void actionTap(By by) {
        findHelper.findElementWithPresenceWait(by);
        findHelper.findElementWitVisibleWait(by);
        WebElement element = findHelper.findElementWitClickableWait(by);
        getTouchActions().singleTap(element).build().perform();
        log.info("'{}' objesine tıklandı.", by);
    }
    public void actionDoubleTap(By by) {
        findHelper.findElementWithPresenceWait(by);
        findHelper.findElementWitVisibleWait(by);
        WebElement element = findHelper.findElementWitClickableWait(by);
        getTouchActions().doubleTap(element).build().perform();
        log.info("'{}' objesine cift tap edildi.", by);
    }

    public void actionMoveDown(int x, int y) {
        getTouchActions().down(x,y).build().perform();
        log.info("{},ve {} kordinatlarına move down edildi.", x,y);
    }
    public void actionMoveUp(int x, int y) {
        getTouchActions().up(x,y).build().perform();
        log.info("{},ve {} kordinatlarına move up edildi.", x,y);
    }
    public void actionFlick(int x, int y) {
        getTouchActions().flick(x,y).build().perform();
        log.info("{},ve {} hiziyla flick edildi.", x,y);
    }
    public void actionFlick(By by,int x, int y,int speed) {
        findHelper.findElementWithPresenceWait(by);
        findHelper.findElementWitVisibleWait(by);
        WebElement element = findHelper.findElementWitClickableWait(by);
        getTouchActions().flick(element,x,y,speed).build().perform();
        log.info("{},ve {} kordinatina {} elementinden {} hiziyla flick edildi.", x,y,by,speed);
    }
    public void actionMoveGeneral(int x, int y) {
        getTouchActions().move(x,y).build().perform();
        log.info("{},ve {} kordinatlarına move edildi.", x,y);
    }
    public void actionScroll(By by,int x, int y) {
        findHelper.findElementWithPresenceWait(by);
        findHelper.findElementWitVisibleWait(by);
        WebElement element = findHelper.findElementWitClickableWait(by);
        getTouchActions().scroll(element,x,y).build().perform();
        log.info("{} elementinden {},ve {} kordinatlarına scroll edildi.", by,x,y);
    }
    public void actionScroll(int x, int y) {
        getTouchActions().scroll(x,y).build().perform();
        log.info("su anki yerden {},ve {} kordinatlarına scroll edildi.",x,y);
    }
    public void actionClickByPoint(int x, int y) {
        //scroll helper will be added!!!
        getTouchActions().moveByOffset(x, y).click().build().perform();
        log.info("{},{} kordinatlarına tıklandı.", x, y);
    }

    // tiklayip basili tutar
    public void actionClickAndHold(By by) throws InterruptedException {
        findHelper.findElementWithPresenceWait(by);
        findHelper.findElementWitVisibleWait(by);
        WebElement element = findHelper.findElementWitClickableWait(by);
        getTouchActions().clickAndHold(element).build().perform();
        log.info("'{}' objesine tıklandı.", by);
    }
    // tiklayip basili tutar
    public void actionLongPress(By by ,int duration) throws InterruptedException {
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

    public void actionDoubleClick(By by) {
        findHelper.findElementWithPresenceWait(by);
        findHelper.findElementWitVisibleWait(by);
        WebElement element = findHelper.findElementWitClickableWait(by);
        getTouchActions().doubleClick(element).build().perform();
        log.info("'{}' objesine cift tiklandi", by);
    }


    public void actionDragDrop(By sourceElementBy, By targetElementBy) {
        findHelper.findElementWithPresenceWait(sourceElementBy);
        findHelper.findElementWitVisibleWait(sourceElementBy);
        //scroll helper will be added!!!
        WebElement element = findHelper.findElementWitClickableWait(sourceElementBy);
        WebElement targetElement = findHelper.findElementWitVisibleWait(targetElementBy);
        getTouchActions().dragAndDrop(element, targetElement).build().perform();
        log.info("{}, elementinin üstünde  basılı tutuldu ve {}, elementinin üstüne kadar sürüklenip bırakıldı.", element, targetElement);
    }

    public void actionDragDropBy(By sourceElementBy, int x, int y) {
        findHelper.findElementWithPresenceWait(sourceElementBy);
        findHelper.findElementWitVisibleWait(sourceElementBy);
        WebElement element = findHelper.findElementWitClickableWait(sourceElementBy);
        getTouchActions().dragAndDropBy(element, x, y).build().perform();
        log.info("{}, elementinin üstünde  basılı tutuldu ve {}, {} kordinatlarına kadar sürüklenip bırakıldı.", element, x, y);
    }




}
