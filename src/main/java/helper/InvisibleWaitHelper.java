package helper;

import driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.concurrent.TimeoutException;

public class InvisibleWaitHelper extends WaitRootHelper {

    /**
     * Belirtilen locatori verilen element kaybolmasi icin verilen sure kadar bekler
     *
     * @param by      Elementin locator bilgisi
     * @param timeout Elementin kaybolmasi olması için harcanak süre, methodu cagirirken verilir bu sure
     * @return boolean deger doner
     */
    public void isInvisible(By by, int timeout) {
        getWebDriverWait(timeout).until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public void isInvisiblePolling(By by, int timeout, int pollingDuration) {
        getWebDriverWait(timeout).pollingEvery(Duration.ofMillis(pollingDuration)).ignoring(NoSuchElementException.class)
                .ignoring(TimeoutException.class).ignoring(Exception.class).until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    /**
     * Belirtilen locatori verilen element kaybolmasi icin verilen sure kadar bekler
     * default timeout direk stabil 30 sn bekler
     *
     * @param by Elementin locator bilgisi
     * @return boolean deger doner
     */
    public void isInvisible(By by) {
        isInvisible(by, default_timeout);
    }


    public void invisibilityOfAllElements(By by, int timeout) {
        getWebDriverWait(timeout).until(
                ExpectedConditions.invisibilityOfAllElements(
                        DriverManager.getInstances().getDriver().findElements(by)));
    }

    public void invisibilityOfAllElementsPolling(By by, int timeout, int pollingDuration) {
        getWebDriverWait(timeout).pollingEvery(Duration.ofMillis(pollingDuration)).ignoring(NoSuchElementException.class)
                .ignoring(TimeoutException.class).ignoring(Exception.class).until(ExpectedConditions.invisibilityOfAllElements(
                DriverManager.getInstances().getDriver().findElements(by)));
    }


    public void invisibilityOfAllElements(By by) {
        invisibilityOfAllElements(by, default_timeout);
    }

    public void isInvisiblePolling(By by, int pollingDuration) {
        isInvisiblePolling(by, default_timeout);
    }

    public void invisibilityOfAllElementsPolling(By by, int pollingDuration) {
        invisibilityOfAllElementsPolling(by, default_timeout, pollingDuration);
    }

}

