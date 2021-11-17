import base.BaseTest;
import com.thoughtworks.gauge.Step;
import helper.*;
import locators.GetLocator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class StepImplementation {
    private final Logger log = LogManager.getLogger(StepImplementation.class);

    //    @Test()
    public void testSon() throws Exception {
        GetLocator getLocator = new GetLocator();
        BaseTest baseTest = new BaseTest();
        baseTest.setUp("ios", "emulatorIos");
        AppHelper appHelper = new AppHelper();
        FindHelper findHelper = new FindHelper();
        String appbund = "com.example.apple-samplecode.UICataloggmmm";
        log.info("app istall edildimi = {}", appHelper.isAppInstalled(appbund));
        TouchActionsHelper touchActionsHelper = new TouchActionsHelper();
        ScrollHelper scrollHelper = new ScrollHelper();
        scrollHelper.iosScrollUntilJS(GetLocator.generateByElement("ACCESSIBILITY_ID", "Buttons"), GetLocator.generateByElement("ACCESSIBILITY_ID", "Web View"), "up");
        System.out.println(findHelper.findElement(GetLocator.generateByElement("ACCESSIBILITY_ID", "Web View")).isDisplayed());
        Thread.sleep(4000);
//        scrollHelper.scrolUntilTargetIOS(GetLocator.generateByElement("ACCESSIBILITY_ID","Buttons"), "name", "label == \"Web View\"");
//        DriverManager.getInstances().getDriver().findElement(GetLocator.generateByElement("ACCESSIBILITY_ID","Web View")).click();

        //app helper tested!!!!
    }

    @Test()
    public void testSonAndroid() throws Exception {
        GetLocator getLocator = new GetLocator();
        BaseTest baseTest = new BaseTest();
        ClickHelper clickHelper = new ClickHelper();
        baseTest.setUp("android", "emulatorAndroid");
        AppHelper appHelper = new AppHelper();
        TouchActionsHelper touchActionsHelper = new TouchActionsHelper();
        FindHelper findHelper = new FindHelper();
        ScrollHelper scrollHelper = new ScrollHelper();
        SwipeHelper swipeHelper = new SwipeHelper();
        DeviceActionsHelper deviceActionsHelper = new DeviceActionsHelper();
        scrollHelper.scrollToText("Views");
        clickHelper.clickElement(GetLocator.generateByElement("TEXT_EQUALS", "Views"));
        scrollHelper.scrollToText("Search View");
        clickHelper.clickElement(GetLocator.generateByElement("TEXT_EQUALS", "Search View"));
        clickHelper.clickElement(GetLocator.generateByElement("TEXT_CONTAINS", "Action Bar"));
        clickHelper.clickElement(GetLocator.generateByElement("ACCESSIBILITY_ID", "Search"));

//        scrollHelper.scrolWithElementAndroid("down","text","Buttons");
//        //app helper tested!!!!
        //find elements tested!!!!


    }

    @AfterEach
    public void teardown() throws Exception {
        BaseTest.tearDown("emulatorAndroid");
    }

    @Step("Launch browser")
    public void implementation1() {

    }

    @Step("Go to <https://www.scrum.org/>")
    public void implementation2(Object arg0) {

    }
}
//app helper tested!!!!
//find elements tested!!!!
//Touch actions helper
//element action tested
//apk info appine bakmadanda biz application package ve activity namelerine ulasabiliriz
//mertmansuroglu@merts-MBP ~ % adb shell
//gm5plus_sprout:/ $ dumpsys window windows | grep -E 'mCurrentFocus'
//  mCurrentFocus=Window{461c96a u0 com.android.launcher3/com.android.searchlauncher.SearchLauncher}
//gm5plus_sprout:/ $