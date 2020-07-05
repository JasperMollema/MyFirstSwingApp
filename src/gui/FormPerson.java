package gui;

public class FormPerson {
    public static final int COLUMN_COUNT = 7;

    public String name;
    public String occupation;
    public String ageCategory;
    public String maritalStatus;
    public String gender;
    public Boolean isClubMember;
    public String memberId;

    public Object getValue(int fieldId) {
        switch (fieldId) {
            case 0 : return name;
            case 1 : return occupation;
            case 2 : return ageCategory;
            case 3 : return maritalStatus;
            case 4 : return gender;
            case 5 : return isClubMember;
            case 6 : return memberId;
            default: return null;
        }
    }
}
