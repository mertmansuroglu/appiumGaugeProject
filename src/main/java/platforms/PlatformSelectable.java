package platforms;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;


public interface PlatformSelectable {

    AppiumDriver<MobileElement> getDriver(String capabilityName) throws Exception;

//kullanacagimiz tum classlarda ayni seyi yapmamiz lazim bu sebeple interface olusturup ordan tureticez diger classlari
}
