package helper;

import driver.DriverManager;
import org.openqa.selenium.JavascriptExecutor;

public class JavascriptHelper {

    public void executeJavascript(String script) {
        ((JavascriptExecutor) DriverManager.getInstances().getDriver()).executeScript(script);
    }

    //asagida birden fazla param veriyoruz objects te bir tane olabilir hic olmayabilir birden fazlada olabilir
    public void executeJavascript(String script, Object... objects) {
        ((JavascriptExecutor) DriverManager.getInstances().getDriver()).executeScript(script, objects);
    }

    public Object getJavascriptResult(String script, Object... objects) {
        return ((JavascriptExecutor) DriverManager.getInstances().getDriver()).executeScript(script, objects);
    }
}

