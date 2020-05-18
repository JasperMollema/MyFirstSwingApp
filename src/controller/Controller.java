package controller;

import gui.FormPerson;
import model.*;

public class Controller {
    private Database database;

    public Controller() {
        database = new Database();
    }
    public void addPerson(FormPerson formPerson) {
        Person person = new Person(
                formPerson.name,
                formPerson.occupation,
                determineAgeCategory(formPerson),
                determineMaritalStatus(formPerson),
                formPerson.isClubMember,
                formPerson.memberId,
                determineGender(formPerson)
        );

        database.addPerson(person);
    }

    private AgeCategory determineAgeCategory(FormPerson formPerson) {
        switch (formPerson.ageCategory) {
            case 0: return AgeCategory.CHILD;
            case 1: return AgeCategory.ADULT;
            case 2: return AgeCategory.SENIOR;
            default:return null;
        }
    }

    private MaritalStatus determineMaritalStatus(FormPerson formPerson) {
        switch (formPerson.maritalStatus) {
            case "single": return MaritalStatus.SINGLE;
            case "married": return MaritalStatus.MARRIED;
            case "cohabiting": return MaritalStatus.COHABITING;
            case "divorced": return MaritalStatus.DIVORCED;
            case "widowed": return MaritalStatus.WIDOWED;
            default: return null;
        }
    }

    private Gender determineGender(FormPerson formPerson) {
        switch (formPerson.gender) {
            case "male" : return Gender.MALE;
            case "female" : return Gender.FEMALE;
            default: return null;
        }
    }
}
