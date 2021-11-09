package platforms;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;


public interface PlatformSelectable {

    AppiumDriver getDriver() throws Exception;

//kullanacagimiz tum classlarda ayni seyi yapmamiz lazim bu sebeple interface olusturup ordan tureticez diger classlari
}
