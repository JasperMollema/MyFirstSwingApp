package controller;

import gui.FormPerson;
import model.AgeCategory;
import model.Gender;
import model.MaritalStatus;
import model.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonControllerHelper {
    private final static String CHILD_VALUE = gui.AgeCategory.CHILD;
    private final static String ADULT_VALUE = gui.AgeCategory.ADULT;
    private final static String SENIOR_VALUE = gui.AgeCategory.SENIOR;

    public static Person formPersonToPerson(FormPerson formPerson) {
        return new Person(
                formPerson.name,
                formPerson.occupation,
                determineAgeCategory(formPerson),
                determineMaritalStatus(formPerson),
                formPerson.isClubMember,
                formPerson.memberId,
                determineGender(formPerson)
        );
    }

    public static List<Person> formPersonListToPersonList(List<FormPerson> formPersonList) {
        if (formPersonList == null) {
            return null;
        }

        List<Person> personList = new ArrayList<>();

        for (FormPerson formPerson : formPersonList) {
            personList.add(formPersonToPerson(formPerson));
        }

        return personList;
    }

    public static FormPerson personToFormPerson(Person person) {
        FormPerson formPerson = new FormPerson();

        formPerson.name = person.getName();
        formPerson.occupation = person.getOccupation();
        formPerson.ageCategory = fillAgeCategory(person.getAgeCategory());
        formPerson.maritalStatus = fillMaritalStatus(person.getMaritalStatus());
        formPerson.isClubMember = person.getIsClubMember();
        formPerson.memberId = person.getMemberID();
        formPerson.gender = fillGender(person.getGender());

        return formPerson;
    }

    public static List<FormPerson> personListToFormPersonList(List<Person> personList) {
        if (personList == null) {
            return null;
        }

        List<FormPerson> formPersonList = new ArrayList<>();

        for (Person person : personList) {
            formPersonList.add(personToFormPerson(person));
        }

        return formPersonList;
    }

    private static String fillMaritalStatus(MaritalStatus maritalStatus) {
        switch (maritalStatus) {
            case SINGLE : return "single";
            case COHABITING: return  "cohabiting";
            case MARRIED: return "married";
            case DIVORCED: return "divorced";
            case WIDOWED: return "widowed";
            default: return "";
        }
    }

    private static String fillGender(Gender gender) {
        switch (gender) {
            case MALE : return "male";
            case FEMALE: return "female";
            default: return "";
        }
    }

    private static String fillAgeCategory(AgeCategory ageCategory) {
        switch (ageCategory) {
            case CHILD : return CHILD_VALUE;
            case ADULT: return ADULT_VALUE;
            case SENIOR: return SENIOR_VALUE;
            default: return null;
        }
    }

    private static AgeCategory determineAgeCategory(FormPerson formPerson) {
        switch (formPerson.ageCategory) {
            case "child": return AgeCategory.CHILD;
            case "adult": return AgeCategory.ADULT;
            case "senior": return AgeCategory.SENIOR;
            default:return null;
        }
    }

    private static MaritalStatus determineMaritalStatus(FormPerson formPerson) {
        switch (formPerson.maritalStatus) {
            case "single": return MaritalStatus.SINGLE;
            case "married": return MaritalStatus.MARRIED;
            case "cohabiting": return MaritalStatus.COHABITING;
            case "divorced": return MaritalStatus.DIVORCED;
            case "widowed": return MaritalStatus.WIDOWED;
            default: return null;
        }
    }

    private static Gender determineGender(FormPerson formPerson) {
        switch (formPerson.gender) {
            case "male" : return Gender.MALE;
            case "female" : return Gender.FEMALE;
            default: return null;
        }
    }
}
