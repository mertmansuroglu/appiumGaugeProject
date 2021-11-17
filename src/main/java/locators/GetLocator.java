package locators;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import driver.DriverManager;
import enums.LocatorType;
import exceptions.KeywordNotFound;
import exceptions.NoSuchSelector;
import io.appium.java_client.MobileBy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class GetLocator {
    // TODO: 14.11.2021  jsonda birden fazla locator koymak istersen nasil alacaz !!!!!!!!!!!!!! name mert1 name mert2
    private static final Logger log = LogManager.getLogger(GetLocator.class);

    public static By generateByElement(String byType, String byValue) throws NoSuchSelector {
        LocatorType locatorType = LocatorType.valueOf(byType.toUpperCase());
        switch (locatorType) {
            case ACCESSIBILITY_ID:
                return MobileBy.AccessibilityId(byValue);
            case ID:
                return By.id(byValue);
            case NAME:
                return MobileBy.name(byValue);
            case CLASS_NAME:
                return MobileBy.className(byValue);
            case XPATH:
                return MobileBy.xpath(byValue);
            case TEXT_EQUALS:
                if (DriverManager.getInstances().getDriver().getClass().getSimpleName().equalsIgnoreCase("AndroidDriver")) {
                    return MobileBy.xpath("//*[@text='" + byValue + "']");
                } else {
                    return MobileBy.xpath("//*[@name='" + byValue + "']");
                }
            case LINK_TEXT:
                return MobileBy.linkText(byValue);
            case ANDROID_UI_AUTOMATOR:
                return MobileBy.AndroidUIAutomator(byValue);
            case PREDICATE_STRING:
                return MobileBy.iOSNsPredicateString(byValue);
            case CLASSCHAIN_IOS:
                return MobileBy.iOSClassChain(byValue);
            case TEXT_CONTAINS:
                if (DriverManager.getInstances().getDriver().getClass().getSimpleName().equalsIgnoreCase("AndroidDriver")) {
                    return MobileBy.xpath("//*[contains(@text,'" + byValue + "')]");
                } else {
                    MobileBy.xpath("//*[contains(@name,'" + byValue + "')]");
                }
            default:
                throw new NoSuchSelector(byType);
        }
    }

    //string kismi jsonin key kismi, json element de value kismi oluyor
    //feature name is jsonj file ismi
    public By getElementBy(String keyword) throws KeywordNotFound {
        JsonObject entries;
        try {
            entries = readJSON(keyword);
        } catch (IOException e) {
            log.error(e.getMessage());
            log.error("{} is not found in file", keyword);
            throw new KeywordNotFound(keyword);
        }
        By by;
        Set<Map.Entry<String, JsonElement>> locators;

        locators = entries.entrySet();
        by = getBy(locators).get(0);

        return by;
    }

    // TODO: 11.11.2021 asagida normalde //  yapmamiz lazimdi neden tek slash??
    private JsonObject readJSON(String keyword) throws IOException {
        Gson gson = new Gson();
        JsonElement jsonObject;
        JsonObject jsonElement;
        //readera json file inin pathini attik
        FileReader reader = new FileReader(
                Objects.requireNonNull(getClass()
                        .getClassLoader()
                        .getResource("jsonFiles/locator.json"))
                        .getPath());

        jsonObject = gson.fromJson(reader, JsonElement.class);
        //json objecti json elemente ceviriyor
        jsonElement = jsonObject.getAsJsonObject();
        return jsonElement.get(keyword).getAsJsonObject();
    }

    private ArrayList<By> getBy(Set<Map.Entry<String, JsonElement>> locators) {
        var by = new ArrayList<By>();
        for (Map.Entry<String, JsonElement> entry : locators) {
            log.info("BY GENERATE :{} - {}", entry.getKey(), entry.getValue());
            try {
                by.add(generateByElement(entry.getKey(), entry.getValue().getAsString()));
            } catch (NoSuchSelector e) {
                log.warn("By not generated :{} - {}", entry.getKey(), entry.getValue());
                e.printStackTrace();
            }
        }
        return by;
    }

    //we use gson for reading the json file
    //gson convert json file to json object
    //gherkin dilinin olayi herkes test yazabilsin


}


//PRESENCE VISIBLE HELPER WAIT HELPER@@@