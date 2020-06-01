package controller;

import gui.FormPerson;
import model.*;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private Database database;
    private boolean tableChanged;
    private boolean personsDeleted;

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
        tableChanged = true;
    }

    public List<FormPerson> getPersonList() {
        List<Person> personList = database.getPersonList();
        List<FormPerson> formPersonList = new ArrayList<>();

        for (Person person : personList) {
            formPersonList.add(fillFormPerson(person));
        }

        return formPersonList;
    }

    public void save() throws SQLException {
        database.save();
    }

    public boolean load() throws SQLException {
        database.load();
        return !database.getPersonList().isEmpty();
    }

    public void connectToDatabase() throws SQLException {
    }

    public void disconnectDatabase() throws SQLException {
    }

    public void savePersonsToFile(File file) throws IOException {
        database.savePersonsToFile(file);
    }

    public List<FormPerson> loadFromFile(File file) throws IOException, ClassNotFoundException {
        database.clear();
        database.loadPersonsFromFile(file);

        return getPersonList();
    }

    private FormPerson fillFormPerson(Person person) {
        FormPerson formPerson = new FormPerson();

        formPerson.id = person.getId();
        formPerson.name = person.getName();
        formPerson.occupation = person.getOccupation();
        formPerson.ageCategory = fillAgeCategory(person.getAgeCategory());
        formPerson.maritalStatus = fillMaritalStatus(person.getMaritalStatus());
        formPerson.isClubMember = person.getIsClubMember();
        formPerson.memberId = person.getMemberID();
        formPerson.gender = fillGender(person.getGender());

        return formPerson;
    }

    public void deletePerson(int row) {
        database.deletePerson(row);
        tableChanged = true;
        personsDeleted = true;
    }

    private String fillMaritalStatus(MaritalStatus maritalStatus) {
        switch (maritalStatus) {
            case SINGLE : return "single";
            case COHABITING: return  "cohabiting";
            case MARRIED: return "married";
            case DIVORCED: return "divorced";
            case WIDOWED: return "widowed";
            default: return "";
        }
    }

    private String fillGender(Gender gender) {
        switch (gender) {
            case MALE : return "male";
            case FEMALE: return "female";
            default: return "";
        }
    }

    private Integer fillAgeCategory(AgeCategory ageCategory) {
        switch (ageCategory) {
            case CHILD : return 0;
            case ADULT: return 1;
            case SENIOR: return 2;
            default: return null;
        }
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

    public boolean isTableChanged() {
        return tableChanged;
    }

    public boolean arePersonsDeleted() {
        return personsDeleted;
    }
}
