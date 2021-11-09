package enums;

public enum PropertiesType {

    BROWSER_NAME("browserName");

    public final String propertiesTypeText;

    PropertiesType(String value) {
        this.propertiesTypeText = value;
    }

    public String getText() {
        return propertiesTypeText;
    }
}
