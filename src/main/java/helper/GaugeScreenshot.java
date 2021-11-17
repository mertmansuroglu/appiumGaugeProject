package helper;

import com.thoughtworks.gauge.screenshot.CustomScreenshotWriter;
import driver.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

public class GaugeScreenshot implements CustomScreenshotWriter {

    //masaustunun fotografini verecek!!! o sebeple bizim gauge in kendi interface ini override e=dip duzenlememiz lazim
    //after stepte bir kez koyuyorum
    @Override
    public String takeScreenshot() {
        var driver = ((TakesScreenshot) DriverManager.getInstances().getDriver());
        var screenshotFileName = String.format("screenshot-%s.png", UUID.randomUUID());
        try {
            Files.write(Path.of(System.getenv("gauge_screenshots_dir"), screenshotFileName),
                    driver.getScreenshotAs(OutputType.BYTES));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return screenshotFileName;
    }
}
