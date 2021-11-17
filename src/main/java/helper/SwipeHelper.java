package helper;

import driver.DriverManager;
import enums.Direction;
import io.appium.java_client.MobileElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;

import java.time.Duration;
import java.util.HashMap;

public class SwipeHelper {
    private final Logger log = LogManager.getLogger(SwipeHelper.class);
    private FindHelper findHelper = new FindHelper();
    private TouchActionsHelper touchActionsHelper = new TouchActionsHelper();

    public static void swipeAndroid(int width, int height, int left, int top, String direction, double percent, Integer speed) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("percent", percent);
        param.put("width", width);
        param.put("height", height);
        param.put("left", left);
        param.put("top", top);
        param.put("direction", direction);
        param.put("speed", speed);
        String script = "mobile: swipeGesture";
        JavascriptHelper javascriptHelper = new JavascriptHelper();
        javascriptHelper.executeJavascript(script, param);
    }

    //TESTED
    public void actionSwipeBetweenTwoElement(By sourceBy, By targetBy) throws InterruptedException {
        MobileElement element = (MobileElement) findHelper.findElementWitClickableWait(sourceBy);
        MobileElement element2 = (MobileElement) findHelper.findElementWitClickableWait(targetBy);
        Point firstPoint = element.getCenter();
        Point secondPoint = element2.getCenter();
        touchActionsHelper.getTouchAction().longPress(PointOption.point(secondPoint.x, secondPoint.y))
                .moveTo(PointOption.point(firstPoint.x, firstPoint.y))
                .release().perform();
        log.info("'{}' objesinden {} objesine swipe edildi.", sourceBy, targetBy);
    }

    public void SwipeUntilBetweenTwoElementsPoint(int startx, int starty, By targetObject) {
        MobileElement element = (MobileElement) findHelper.findElementWitClickableWait(targetObject);
        Point secondPoint = element.getCenter();
        touchActionsHelper.getTouchAction().longPress(PointOption.point(secondPoint.x, secondPoint.y))
                .moveTo(PointOption.point(startx, starty))
                .release().perform();
    }

    public void actionSwipeUpDownByElement(By sourceBy, double percent) throws InterruptedException {
        MobileElement element = (MobileElement) findHelper.findElementWitClickableWait(sourceBy);
        Point firstPoint = element.getCenter();
        int starty = firstPoint.y;
        int x = firstPoint.x;
        int endy = (int) (starty * percent);
        touchActionsHelper.getTouchAction().longPress(PointOption.point(x, starty))
                .moveTo(PointOption.point(x, endy))
                .release().perform();
        log.info("'{}' objesinden {} percent kadar swipe up/down edildi.", sourceBy, percent);
    }

    public void actionSwipeRightLeftByElement(By sourceBy, double percent) throws InterruptedException {
        MobileElement element = (MobileElement) findHelper.findElementWitClickableWait(sourceBy);
        Point firstPoint = element.getCenter();
        int startx = firstPoint.x;
        int y = firstPoint.y;
        int endx = (int) (startx * percent);
        touchActionsHelper.getTouchAction().longPress(PointOption.point(startx, y))
                .moveTo(PointOption.point(endx, y))
                .release().perform();
        log.info("'{}' objesinden {} percent kadar right/left swipe edildi.", sourceBy, percent);
    }

//icinde bulundugun threadde== { } bracketlerin icerisindekileri alirsin

    public void swipeByPoint(int pressTime, Direction direction, int width, int height) {
        System.out.println("\n swipeScreen(): dir: '" + direction + "'"); // always log your actions
        Point pointStart, pointEnd;
        PointOption pointOptionStart, pointOptionEnd;
        int endX, endY;
        var size = DriverManager.getInstances().getDriver().manage().window().getSize();
        switch (direction) {
            /**
             * Diyelim ekranin tum yuksekligi(width) 100 olsun, bizim elementte height 50 olsun
             * biz 50 den 100 e cekersek Swipe hand to up olmus olur!!!!
             */
            case UP: // center of footer
                endY = size.height;
                pointStart = new Point(width, height);
                pointEnd = new Point(width, endY);
                break;
            /**Diyelim ekranin tum yukseligi(width) 100 olsun,
             *  bizim elementin width ve heighti((5)) var
             *  end y demek ekranin yuksekliginin 0.1 i yani 1 dir
             *  height ise 5 ti biz 5 ten 1 e cekersek swipe hand to DOWN yapmis oluruz!!
             */
            case DOWN:
                endY = (int) (size.height * 0.10);
                pointEnd = new Point(width, endY);
                pointStart = new Point(width, height);
                break;
            /**
             * Diyelim ekranin tum genisligi(width) 100 olsun,
             * bizim elementin width(5) ve heighti var
             *end x demek ekranin genisliginin 0.1 i yani 1 dir!!!
             *width ise 5 ti biz 5 ten 1 e cekersek swipe hand to left yapmis oluruz!!
             */
            case LEFT:
                endX = (int) (size.width * 0.10);
                pointStart = new Point(width, height);
                pointEnd = new Point(endX, height);
                break;
            /**
             * Diyelim ekranin tum genisligi(width) 100 olsun, bizim elementte width 50 olsun
             * biz 50 den 100 e cekersek Swipe hand to right olmus olur!!!!
             */
            case RIGHT:
                endX = DriverManager.getInstances().getDriver().manage().window().getSize().width;
                pointStart = new Point(width, height);
                pointEnd = new Point(endX, height);
                break;
            default:
                throw new IllegalArgumentException("swipeScreen(): dir: '" + direction.toString() + "' NOT supported");
        }
// execute swipe using TouchAction
        pointOptionStart = PointOption.point(pointStart.x, pointStart.y);
        pointOptionEnd = PointOption.point(pointEnd.x, pointEnd.y);
        //swipe yapamadiig zaman yapacagi isleme
        try {
            touchActionsHelper.getTouchAction()
                    .press(pointOptionStart)
                    // a bit more reliable when we add small wait
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(pressTime)))
                    .moveTo(pointOptionEnd)
                    .release().perform();
        } catch (Exception e) {
            System.err.println("swipeScreen(): TouchAction FAILED\n" + e.getMessage());
            return;
        }
    }

    public void swipeUntilAllPlatform(By sourceObject, By targetObject, Direction direction, int pressTime) throws InterruptedException {
        MobileElement element = (MobileElement) findHelper.findElementWitClickableWait(sourceObject);
        int width = element.getCenter().x;
        int height = element.getCenter().y;
        swipeUntilAllPlatformPoint(width, height, targetObject, direction, pressTime);
    }

    ////
    public void swipeUntilAllPlatformPoint(int width, int height, By targetObject, Direction direction, int pressTime) throws InterruptedException {
        boolean isDisplayed;
        if ((findHelper.findElements(targetObject).size() == 1)) {
            SwipeUntilBetweenTwoElementsPoint(width, height, targetObject);
        }
        int i = 0;
        do {
            swipeByPoint(pressTime, direction, width, height);
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

    //asagidaki androiodde yok
    public void swipeIOS(String direction) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        switch (direction) {
            case "down":
                param.put("direction", "down");
                break;
            case "up":
                param.put("direction", "up");
                break;
            case "left":
                param.put("direction", "left");
                break;
            case "right":
                param.put("direction", "right");
                break;
            default:
                throw new IllegalArgumentException("mobileSwipeScreenIOS(): dir: '" + direction + "' NOT supported");
        }
        try {
            JavascriptHelper javascriptHelper = new JavascriptHelper();
            javascriptHelper.executeJavascript("mobile:swipe", param);
        } catch (Exception e) {
            System.err.println("mobileSwipeScreenIOS(): FAILED" + e.getMessage());
        }
    }

    public void iosSwipeUntilJS(By sourceObject, By targetObject, String direction) throws InterruptedException {
        SwipeHelper swipeHelper = new SwipeHelper();
        boolean isDisplayed;
        if ((findHelper.findElements(targetObject).size() == 1)) {
            swipeHelper.actionSwipeBetweenTwoElement(sourceObject, targetObject);
        }
        int i = 0;
        //while yapmiyoruz cunku displayed degilse hata verir!!
        //isdisplayed kullaniriz cunku bir daha swipe etmemeliyiz!!!
        //while() sart dogru oldugu surece calisir
        do {
            i++;
            if (i == 15) {
                break;
            }
            swipeIOS(direction);
            try {
                //eger true donerse zaten donguden cikacak false ise zaten yakaliyoruz biz!!
                isDisplayed = findHelper.findElement(targetObject).isDisplayed();
            } catch (Exception e) {
                isDisplayed = false;
            }
        } while (!isDisplayed);
    }

    public void androidSwipeUntilJS(By swipeableObject, By targetObject, double percent, int speed, String direction) throws InterruptedException {
        SwipeHelper swipeHelper = new SwipeHelper();
        MobileElement element = (MobileElement) findHelper.findElementWitClickableWait(swipeableObject);
        int width = element.getCenter().x;
        int height = element.getCenter().y;
        int left = element.getCenter().getX();
        int top = element.getCenter().getY();
        boolean isDisplayed;
        if ((findHelper.findElements(targetObject).size() == 1)) {
            swipeHelper.actionSwipeBetweenTwoElement(swipeableObject, targetObject);
        }
        int i = 0;
        do {
            i++;
            if (i == 15) {
                break;
            }
            swipeAndroid(width, height, left, top, direction, percent, speed);
            try {
                //eger true donerse zaten donguden cikacak false ise zaten yakaliyoruz biz!!
                isDisplayed = findHelper.findElement(targetObject).isDisplayed();
            } catch (Exception e) {
                isDisplayed = false;
            }
        } while (!isDisplayed);
    }


}


