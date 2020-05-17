import java.util.EventObject;

public class FormEvent extends EventObject {
    private String name;
    private String occupation;
    private int ageCategory;
    private String maritalStatus;

    public FormEvent(FormPanel formPanel, String name, String occupation,
                     int ageCategory, String maritalStatus) {
        super(formPanel);
        this.name = name;
        this.occupation = occupation;
        this.ageCategory = ageCategory;
        this.maritalStatus = maritalStatus;
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
}
