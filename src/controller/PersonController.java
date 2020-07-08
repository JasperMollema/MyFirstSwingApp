package controller;

import gui.FormPerson;
import model.DatabaseAcces;
import model.FileSaver;
import model.Person;
import model.PersonSearchList;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonController {
    private boolean tableChanged;
    private boolean personsDeleted;
    private PersonSearchList personSearchList;
    private FileSaver fileSaver;
    private List<Person> personList;

    public PersonController() {
        personSearchList = new PersonSearchList();
        fileSaver = new FileSaver();
        personList = new ArrayList<>();
    }

    public void disconnectDatabase() throws SQLException {
        new DatabaseAcces().disconnect();
    }

    public void addPerson(FormPerson formPerson) {
        Person person = PersonControllerHelper.formPersonToPerson(formPerson);

        personSearchList.addPerson(person);
        refillPersonList();
        tableChanged = true;
    }

    public void deletePerson(int row) {
        personSearchList.deletePerson(row);
        refillPersonList();
        tableChanged = true;
        personsDeleted = true;
    }

    public List<FormPerson> getFormPersonList() {
        return PersonControllerHelper.personListToFormPersonList(personList);
    }

    public void save() throws SQLException {
        personSearchList.save();
        refillPersonList();
        tableChanged = false;
        personsDeleted = false;
    }

    public boolean load() throws SQLException {
        personSearchList.findPersons();
        refillPersonList();
        return !personList.isEmpty();
    }

    public void update(int row, FormPerson updatedFormPerson) {
        tableChanged = true;
        // Row in the table corresponds with the index in the personlist.
        personSearchList.update(row, PersonControllerHelper.formPersonToPerson(updatedFormPerson));
        refillPersonList();
    }

    public void goToPreviousUpdate() {
        personSearchList.goToPreviousUpdate();
        refillPersonList();
    }

    public void goToNextUpdate() {
        personSearchList.goToNextUpdate();
        refillPersonList();
    }

    public boolean hasPreviousUpdate() {
        return personSearchList.hasPreviousUpdate();
    }

    public boolean hasNextUpdate() {
        return personSearchList.hasNextUpdate();
    }

    public void savePersonsToFile(File file) throws IOException {
        fileSaver.savePersonsToFile(file);
    }

    public List<FormPerson> loadFromFile(File file) throws IOException, ClassNotFoundException {
        personList.clear();
        fileSaver.loadPersonsFromFile(file);
        personList = fileSaver.getResult();

        return getFormPersonList();
    }

    private void refillPersonList() {
        personList = personSearchList.getPersons();
    }

    public boolean isTableChanged() {
        return tableChanged;
    }

    public boolean arePersonsDeleted() {
        return personsDeleted;
    }
}
