package model;

public class Person {
    private static int count;

    private int id;
    private String name;
    private String occupation;
    private AgeCategory ageCategory;
    private MaritalStatus maritalStatus;
    private Boolean isClubMember;
    private String memberID;
    private Gender gender;

    public Person(String name, String occupation, AgeCategory ageCategory, MaritalStatus maritalStatus,
                  Boolean isClubMember, String memberID, Gender gender) {
        this.name = name;
        this.occupation = occupation;
        this.ageCategory = ageCategory;
        this.maritalStatus = maritalStatus;
        this.isClubMember = isClubMember;
        this.memberID = memberID;
        this.gender = gender;

        this.id = count++;
    }

    @Override
    public String toString() {
        return "name : " + name;
    }
}
