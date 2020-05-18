package gui;

public class FormPerson {
    public static final int COLUMN_COUNT = 8;

    public Integer id;
    public String name;
    public String occupation;
    public Integer ageCategory;
    public String maritalStatus;
    public String gender;
    public Boolean isClubMember;
    public String memberId;

    public Object getValue(int fieldId) {
        switch (fieldId) {
            case 0 : return id;
            case 1 : return name;
            case 2 : return occupation;
            case 3 : return ageCategory;
            case 4 : return maritalStatus;
            case 5 : return gender;
            case 6 : return isClubMember;
            case 7 : return memberId;
            default: return null;
        }
    }
}
