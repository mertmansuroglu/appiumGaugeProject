package helper;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class PresenceWaitHelper extends WaitRootHelper {

    /**
     * asagidaki method yollanan by elementini app uzerinde olmasını belirtilen sure kadar bekler
     *
     * @param by      Elementin locator bilgisi
     * @param timeout Elementleri bulmak için harcanan süre , methodu cagirirken verilir bu sure
     */

    protected WebElement waitUntilPresence(By by, int timeout) {
        return getWebDriverWait(timeout).until(ExpectedConditions.presenceOfElementLocated(by));
    }


    protected WebElement waitUntilPresencePolling(By by, int timeout, int pollingDuration) {
        return getWebDriverWait(timeout).pollingEvery(Duration.ofMillis(pollingDuration)).ignoring(NoSuchElementException.class)
                .ignoring(TimeoutException.class).ignoring(Exception.class).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    /**
     * Locator'ı yollanan elementlerin sayfa üzerinde var olmasını bekler
     *
     * @param by      Elementlerin locator bilgisi
     * @param timeout Elementleri bulmak için harcanak süre
     */
    protected List<WebElement> waitUntilPresenceAll(By by, int timeout) {
        return getWebDriverWait(timeout).until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    protected List<WebElement> waitUntilPresenceAllPolling(By by, int timeout, int pollingDuration) {
        return getWebDriverWait(timeout).pollingEvery(Duration.ofMillis(pollingDuration)).ignoring(NoSuchElementException.class)
                .ignoring(TimeoutException.class).ignoring(Exception.class).until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }


    /**
     * Locator'ı yollanan elementin sayfa üzerinde var olmasını bekler
     * default timeout direk stabil 30 sn bekler
     *
     * @param by Elementin locator bilgisi
     */
    protected WebElement waitUntilPresence(By by) {
        return waitUntilPresence(by, DEFAULT_TIMEOUT);
    }

    /**
     * Locator'ı yollanan elementlerin sayfa üzerinde var olmasını bekler
     *
     * @param by Elementlerin locator bilgisi
     */
    protected List<WebElement> waitUntilPresenceAll(By by) {
        return waitUntilPresenceAll(by, DEFAULT_TIMEOUT);
    }

    protected WebElement waitUntilPresencePolling(By by, int pollingDuration) {
        return waitUntilPresencePolling(by, DEFAULT_TIMEOUT, pollingDuration);
    }

    protected List<WebElement> waitUntilPresenceAllPolling(By by, int pollingDuration) {
        return waitUntilPresenceAllPolling(by, DEFAULT_TIMEOUT, pollingDuration);
    }

    protected List<WebElement> waitUntilPresenceAllPolling(By by) {
        return waitUntilPresenceAllPolling(by, DEFAULT_TIMEOUT, SLEEP_IN_MILLS);
    }

    protected WebElement waitUntilPresencePolling(By by) {
        return waitUntilPresencePolling(by, DEFAULT_TIMEOUT, SLEEP_IN_MILLS);
    }
}
