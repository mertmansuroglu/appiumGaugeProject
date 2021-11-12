package helper;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class VisibleWaitHelper extends WaitRootHelper {
// TODO: 12.11.2021 asagida public yaptik protected yapmadim impten cagirmam gerekirse ?

    /**
     * Locator'ı yollanan elementin app üzerinde görünür olmasını bekler
     *
     * @param by      Elementin locator bilgisi
     * @param timeout Elementi bulmak için harcanak süre, methodu cagirirken verilir bu sure
     */
    protected WebElement waitUntilVisible(By by, int timeout) {
        return getWebDriverWait(timeout).until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    protected WebElement waitUntilVisiblePolling(By by, int timeout, int pollingDuration) {
        return getWebDriverWait(timeout).pollingEvery(Duration.ofMillis(pollingDuration)).ignoring(NoSuchElementException.class)
                .ignoring(TimeoutException.class).ignoring(Exception.class).until(ExpectedConditions.visibilityOfElementLocated(by));
    }


//By making use of Wait object, I have created an another helper methods composed of VisibleWait,PollingWait,PresenceWait and Invisible

    /**
     * Locator'ı yollanan elementin app üzerinde görünür olmasını bekler
     *
     * @param by Elementin locator bilgisi
     */
    protected WebElement waitUntilVisible(By by) {
        return waitUntilVisible(by, default_timeout);
    }

    /**
     * Locator'ı yollanan elementlerin app üzerinde görünür olmasını bekler
     *
     * @param by      Elementlerin locator bilgisi
     * @param timeout Elementleri bulmak için harcanak süre
     */
    protected List<WebElement> waitUntilVisibleAll(By by, int timeout) {
        return getWebDriverWait(timeout).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }

    protected List<WebElement> waitUntilVisibleAllPolling(By by, int timeout, int pollingDuration) {
        return getWebDriverWait(timeout).pollingEvery(Duration.ofMillis(pollingDuration)).ignoring(NoSuchElementException.class)
                .ignoring(TimeoutException.class).ignoring(Exception.class).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }


    /**
     * Locator'ı yollanan elementlerin app üzerinde görünür olmasını bekler
     *
     * @param by Elementlerin locator bilgisi
     */
    protected List<WebElement> waitUntilVisibleAll(By by) {
        return waitUntilVisibleAll(by, default_timeout);
    }

    protected WebElement waitUntilVisiblePolling(By by, int pollingDuration) {
        return waitUntilVisiblePolling(by, default_timeout, pollingDuration);
    }

    protected List<WebElement> waitUntilVisibleAllPolling(By by, int pollingDuration) {
        return waitUntilVisibleAllPolling(by, default_timeout, pollingDuration);
    }
}