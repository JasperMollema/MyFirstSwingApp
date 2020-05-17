import java.util.EventObject;

public class FormEvent extends EventObject {
    private String name;
    private String occupation;
    private int ageCategory;
    private String maritalStatus;
    private Boolean isClubMember;
    private String memberID;

    public FormEvent(FormPanel formPanel, String name, String occupation, int ageCategory,
                     String maritalStatus, boolean isClubMember, String memberID) {
        super(formPanel);
        this.name = name;
        this.occupation = occupation;
        this.ageCategory = ageCategory;
        this.maritalStatus = maritalStatus;
        this.isClubMember = isClubMember;
        this.memberID = memberID;
    }

    public String getName() {
        return name;
    }

    public String getOccupation() {
        return occupation;
    }

    public int getAgeCategory() {
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
}
