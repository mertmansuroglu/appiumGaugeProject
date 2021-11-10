package locators;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import enums.LocatorType;
import enums.RelativeType;
import exceptions.KeywordNotFound;
import exceptions.NoSuchSelector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class GetLocator {
    //FILE READERI MOCKLAMAMIZ LAZIM!!!
    //GSON METHODUNU MOCKLAMAM LAZIM
    private static final Logger log = LogManager.getLogger(GetLocator.class);

    //string kismi jsonin key kismi, json element de value kismi oluyor
    //feature name is jsonj file ismi
    public By getElementBy(String keyword) throws KeywordNotFound {
        JsonObject entries;
        try {
            entries = readJSON(keyword);
        } catch (IOException e) {
            log.error(e.getMessage());
            log.error("{} is not found in file",keyword);
            throw new KeywordNotFound(keyword);
        }
        By by;
        Set<Map.Entry<String, JsonElement>> locators;

            locators = entries.entrySet();
            by = getBy(locators).get(0);

        return by;
    }


    //we use gson for reading the json file
    //gson convert json file to json object
    //gherkin dilinin olayi herkes test yazabilsin
    private JsonObject readJSON(String keyword) throws IOException {
        Gson gson = new Gson();
        JsonElement jsonObject;
        JsonObject jsonElement;
        //readera json file inin pathini attik
        FileReader reader = new FileReader(
                Objects.requireNonNull(getClass()
                                .getClassLoader()
                                .getResource("locators/GetLocator.json"))
                        .getPath());

        jsonObject = gson.fromJson(reader, JsonElement.class);
        //json objecti json elemente ceviriyor
        jsonElement = jsonObject.getAsJsonObject();
        return jsonElement.get(keyword).getAsJsonObject();
    }

    private static By generateByElement(String byType, String byValue) throws NoSuchSelector {
        LocatorType locatorType = LocatorType.valueOf(byType.toUpperCase());
        switch (locatorType) {
            case ID:
                return By.id(byValue);
            case NAME:
                return By.name(byValue);
            case CLASS_NAME:
                return By.className(byValue);
            case XPATH:
                return By.xpath(byValue);
            case TEXT_EQUALS:
                return By.xpath("//*[text()='" + byValue + "']");
            case LINK_TEXT:
                return By.linkText(byValue);
            case PARTIAL_LINK_TEXT:
                return By.partialLinkText(byValue);
            case CONTAINS:
                return By.xpath("//*[contains(@id, '" + byValue + "')] or " +
                        "//*[contains(@class, '" + byValue + "')]" +
                        "//*[contains(@tag, '" + byValue + "')]" +
                        "//*[contains(@span, '" + byValue + "')]" +
                        "//*[contains(@name, '" + byValue + "')]" +
                        "//*[contains(text(), '" + byValue + "')]");
            default:
                throw new NoSuchSelector(byType);
        }
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
}

//PRESENCE VISIBLE HELPER WAIT HELPER@@@