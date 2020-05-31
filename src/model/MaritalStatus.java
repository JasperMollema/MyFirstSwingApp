package model;

public enum MaritalStatus {
    SINGLE, MARRIED, COHABITING, DIVORCED, WIDOWED;

    public static MaritalStatus getMaritalStatus(String maritalStatus) {
        return valueOf(maritalStatus.toUpperCase().trim());
    }
}
