package helper;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.concurrent.TimeoutException;

public class ClickableWaitHelper extends WaitRootHelper {


    /**
     * asagidaki method yollanan by elementini app uzerinde clickable olmasina kadar bekler
     * Locator'ı yollanan elementin app üzerinde tıklanabilir olmasını bekler
     *
     * @param by      Elementin locator bilgisi
     * @param timeOut Elementin tıklanabilir olması için harcanak süre, methodu cagirirken verilir bu sure
     */
    protected WebElement waitUntilClickable(By by, int timeOut) {

        return getWebDriverWait(timeOut, SLEEP_IN_MILLS).until(ExpectedConditions.elementToBeClickable(by));

    }

    protected WebElement waitUntilClickablePolling(By by, int timeout, int pollingDuration) {
        return getWebDriverWait(timeout).pollingEvery(Duration.ofMillis(pollingDuration)).ignoring(NoSuchElementException.class)
                .ignoring(TimeoutException.class).ignoring(Exception.class).until(ExpectedConditions.elementToBeClickable(by));
    }


    /**
     * Locator'ı yollanan elementin app üzerinde tıklanabilir olmasını bekler
     * default timeout direk stabil 30 sn bekler
     *
     * @param by Elementin locator bilgisi
     */
    protected WebElement waitUntilClickable(By by) {
        return waitUntilClickable(by, DEFAULT_TIMEOUT);
    }

    protected WebElement waitUntilClickablePolling(By by, int pollingDuration) {
        return waitUntilClickablePolling(by, DEFAULT_TIMEOUT, pollingDuration);
    }


}