package helper;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.concurrent.TimeoutException;

public class TextWaitHelper extends WaitRootHelper {

// TODO: 11.11.2021 burayi sor nedne void?? nasil kullaniriz!!! boolean koymak gerekli mi ?

    /**
     * asagidaki method yollanan by elementini app uzerinde clickable olmasina kadar bekler
     * Locator'ı yollanan elementin app üzerinde tıklanabilir olmasını bekler
     *
     * @param by      Elementin locator bilgisi
     * @param timeOut Elementin tıklanabilir olması için harcanak süre, methodu cagirirken verilir bu sure
     */
    public void waitUntilTextToBePresentInElement(By by, int timeOut, String text) {

        getWebDriverWait(timeOut, SLEEP_IN_MILLS).until(ExpectedConditions.textToBePresentInElementLocated(by, text));
    }

    public void waitUntilTextToBePresentInElementPolling(By by, int timeout, int pollingDuration, String text) {
        getWebDriverWait(timeout).pollingEvery(Duration.ofMillis(pollingDuration)).ignoring(NoSuchElementException.class)
                .ignoring(TimeoutException.class).ignoring(Exception.class).until(ExpectedConditions.textToBePresentInElementLocated(by, text));
    }

    /**
     * Locator'ı yollanan elementin app üzerinde tıklanabilir olmasını bekler
     * default timeout direk stabil 30 sn bekler
     *
     * @param by Elementin locator bilgisi
     */
    public void waitUntilTextToBePresentInElement(By by, String text) {
        waitUntilTextToBePresentInElement(by, DEFAULT_TIMEOUT, text);
    }

    public void waitUntilTextToBePresentInElementPolling(By by, String text, int pollingDuration) {
        waitUntilTextToBePresentInElementPolling(by, DEFAULT_TIMEOUT, pollingDuration, text);
    }
}