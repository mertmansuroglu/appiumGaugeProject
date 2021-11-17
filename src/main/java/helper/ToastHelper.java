package helper;

import exceptions.NoSuchSelector;
import locators.GetLocator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ToastHelper {
    private final Logger log = LogManager.getLogger(ToastHelper.class);
    private FindHelper findHelper = new FindHelper();

    public WebElement getToastElement() throws NoSuchSelector {

        return findHelper.findElementWithVisiblePolling(GetLocator.generateByElement("XPATH", "//android.widget.Toast[1]"));
    }

    public List<WebElement> getToastElements() throws NoSuchSelector {
        return (List<WebElement>) findHelper.findElementsWithPresencePolling(GetLocator.generateByElement("XPATH", "//android.widget.Toast[1]"));

    }

    public void toastActionVerify(String toastMessage, By by) throws NoSuchSelector {

        while (getToastElements().size() == 0) {
            findHelper.findElement(by).click();
            try {
                if (!getToastElement().isDisplayed()) {
                    findHelper.findElement(by).click();
                }
            } catch (StaleElementReferenceException e) {
                System.out.println(e.getMessage());
            }
        }
        String actual = getToastElement().getText();
        Assertions.assertTrue(toastMessage.equalsIgnoreCase(actual));

    }
}

