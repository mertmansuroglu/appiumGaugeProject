import capabilities.GetCapabilities;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.TableRow;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

public class StepImplementation {

private GetCapabilities getCapabilities;
    @Step("Launch browser")
    public void implementation1() throws Exception {
        DesiredCapabilities cap = getCapabilities.getDesiredCapabilities("iPhone_13_installed");
        System.out.println(cap);

    }

    @Step("Go to <https://www.scrum.org/>")
    public void implementation2(Object arg0) {

    }
}
