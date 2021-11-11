import com.thoughtworks.gauge.Step;
import driver.DriverManager;
import locators.GetLocator;
import org.junit.jupiter.api.Test;

public class StepImplementation {


    @Test()
    public void testSon() throws Exception {

        GetLocator getLocator = new GetLocator();
        getLocator.getElementBy("test name");

        DriverManager.getInstances().getDriver().findElement(getLocator.getElementBy("test name")).click();

    }

    @Step("Launch browser")
    public void implementation1() {

    }

    @Step("Go to <https://www.scrum.org/>")
    public void implementation2(Object arg0) {

    }
}
