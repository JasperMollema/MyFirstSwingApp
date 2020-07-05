package gui;

import java.util.EventObject;

public class FormEvent extends EventObject {
    private String name;
    private String occupation;
    private String ageCategory;
    private String maritalStatus;
    private Boolean isClubMember;
    private String memberID;
    private String gender;

    public FormEvent(FormPanel formPanel, String name, String occupation, String ageCategory,
                     String maritalStatus, boolean isClubMember, String memberID, String gender) {
        super(formPanel);
        this.name = name;
        this.occupation = occupation;
        this.ageCategory = ageCategory;
        this.maritalStatus = maritalStatus;
        this.isClubMember = isClubMember;
        this.memberID = memberID;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getAgeCategory() {
        return ageCategory;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public Boolean isClubMember() {
        return isClubMember;
    }

    public String getMemberID() {
        return memberID;
    }

    public String getGender() {
        return gender;
    }
}
