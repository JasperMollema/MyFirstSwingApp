package controller;

import gui.FormPerson;
import model.DatabaseAcces;
import model.FileSaver;
import model.Person;
import model.PersonSearchList;
import model.personList.PersonListModifier;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonController {
    private PersonSearchList personSearchList;
    private FileSaver fileSaver;
    private PersonListModifier personListModifier;
    private List<Person> personList;
    private boolean tableChanged;
    private boolean personsDeleted;

    public PersonController() {
        personSearchList = new PersonSearchList();
        personList = new ArrayList<>();
        personListModifier = new PersonListModifier(personList);
        fileSaver = new FileSaver();
    }

    public boolean loadPersons() throws SQLException {
        personSearchList.findPersons();
        personList = personSearchList.getResult();
        return !personList.isEmpty();
    }

    public void savePersons() throws SQLException{}

    public void addPerson(FormPerson formPerson) {
        Person person = PersonControllerHelper.formPersonToPerson(formPerson);
        updatePersonList();
        tableChanged = true;
    }

    public void updatePerson(int row, FormPerson updatedFormPerson) {
        Person originalPerson = personList.get(row);
        Person updatedPerson = PersonControllerHelper.formPersonToPerson(updatedFormPerson);
        tableChanged = true;
        updatePersonList();
    }

    public void deletePerson(int row) {
        Person person = personList.get(row);
        updatePersonList();
        tableChanged = true;
        personsDeleted = true;
    }

    public void undo() {
        updatePersonList();
    }

    public void redo() {
        updatePersonList();
    }

    private void updatePersonList() {
        personList = personListModifier.getPersonList();
    }

    public boolean hasPreviousUpdate() {
        return true;
    }

    public boolean hasNextUpdate() {
        return true;
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

    public List<FormPerson> getFormPersonList() {
        return PersonControllerHelper.personListToFormPersonList(personList);
    }

    public void disconnectDatabase() throws SQLException {
        new DatabaseAcces().disconnect();
    }

    public boolean isTableChanged() {
        return tableChanged;
    }

    public boolean arePersonsDeleted() {
        return personsDeleted;
    }
}
