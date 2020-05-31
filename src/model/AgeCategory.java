package model;

public enum AgeCategory {
    CHILD, ADULT, SENIOR;

    public static AgeCategory getAgeCategory(String ageCategory) {
        return valueOf(ageCategory.toUpperCase().trim());
    }
}
