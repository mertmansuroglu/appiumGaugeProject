package helper;

import driver.DriverManager;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitRootHelper {

    protected static final int default_timeout = 30;
    protected static final int sleepInMills = 40;

    /**
     * Asagida ki method her call edilidingde wait objesi olusturur //WebDriverWait(driver,30) e karsilik gelir
     *
     * @return wait objesi doner
     */
    protected WebDriverWait getWebDriverWait() {
        return getWebDriverWait(default_timeout, sleepInMills);
    }

    protected WebDriverWait getWebDriverWait(int timeout) {
        return getWebDriverWait(timeout, sleepInMills);

    }

    /**
     * YUkaridaki ayni isi yapiyorum ama default sleep in milli9s te yonetiriz asagida
     * Asagida ki method her call edilidingde wait objesi olusturur
     *
     * @param timeout       integer wait suresi
     * @param sleepInMillis wait arasindaki integer sleep suresi
     * @return wait objesi doner
     */

    //HEPSI BUNDAN TURUYOR!!!
    //O YUZDEN parametreye vermene gerek yok
    //fluent wait ve webdriver wait mantigi ayni webdriver wait fluent waiti extend ediyor zaten
    protected WebDriverWait getWebDriverWait(int timeout, int sleepInMillis) {
        return new WebDriverWait(DriverManager.getInstances().getDriver(), timeout, sleepInMillis);
    }


}
