package enums;

public enum LocatorType {

    ID("id"),
    XPATH("xpath"),
    NAME("name"),
    CLASS_NAME("className"),
    LINK_TEXT("linkText"),
    TEXT_CONTAINS("contains"),
    ACCESSIBILITY_ID("accessibilityId"),
    PREDICATE_STRING("predicateString"),
    TEXT_EQUALS("textEquals"),
    CLASSCHAIN_IOS("classchainIos"),
    ANDROID_UI_AUTOMATOR("androidUiAutomator");

    private final String locatorTypeText;

    LocatorType(String value) {
        this.locatorTypeText = value;
    }

    public String getText() {
        return locatorTypeText;
    }
}

