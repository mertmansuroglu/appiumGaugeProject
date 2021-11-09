package enums;

public enum LocatorType {

    ID("id"),
    XPATH("xpath"),
    NAME("name"),
    CLASS_NAME("className"),
    LINK_TEXT("linkText"),
    PARTIAL_LINK_TEXT("partialLinkText"),
    CONTAINS("contains"),
    TEXT_EQUALS("textEquals");

    private final String locatorTypeText;

    LocatorType(String value) {
        this.locatorTypeText = value;
    }

    public String getText() {
        return locatorTypeText;
    }
}

