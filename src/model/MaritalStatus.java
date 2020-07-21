package model;

public enum MaritalStatus {
    SINGLE("single"), MARRIED("married"), COHABITING("co-habiting"),
    DIVORCED("divorced"), WIDOWED("widowed");

    private String stringValue;

    private MaritalStatus(String stringValue) {
        this.stringValue = stringValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public static MaritalStatus getMaritalStatus(String maritalStatus) {
        return valueOf(maritalStatus.toUpperCase().trim());
    }
}
