package model;

public enum Gender {
    MALE, FEMALE;

    public static Gender getGender(String gender) {
        return valueOf(gender.toUpperCase().trim());
    }
}
