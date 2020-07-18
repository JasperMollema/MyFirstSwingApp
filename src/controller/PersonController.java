package controller;

import gui.FormPerson;
import model.DatabaseAcces;
import model.FileSaver;
import model.Person;
import model.PersonSearchList;
import model.personList.PersonList;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PersonController {
    private PersonSearchList personSearchList;
    private FileSaver fileSaver;
    private PersonList personList;

    public PersonController() {
        personSearchList = new PersonSearchList();
        personList = new PersonList();
        fileSaver = new FileSaver();
    }

    public boolean loadPersons() throws SQLException {
        personSearchList.findPersons();
        personList.clear();
        personList.loadPersons(personSearchList.getResult());
        return !personSearchList.getResult().isEmpty();
    }

    public void savePersons() throws SQLException{}

    public void addPerson(FormPerson formPerson) {
        Person person = PersonControllerHelper.formPersonToPerson(formPerson);
        personList.addPerson(person);
    }

    public void changePerson(int row, FormPerson changedFormPerson) {
        Person changedPerson = PersonControllerHelper.formPersonToPerson(changedFormPerson);
        personList.changePerson(row, changedPerson);
    }

    public void deletePerson(int row) {
        personList.deletePerson(row);
    }

    public void undo() {
        personList.undoLastChange();
    }

    public void redo() {
        personList.redoLastChange();
    }

    public boolean hasPreviousUpdate() {
        return personList.hasPreviousChange();
    }

    public boolean hasNextUpdate() {
        return personList.hasNextChange();
    }

    public void savePersonsToFile(File file) throws IOException {
        fileSaver.savePersonsToFile(file);
    }

    public List<FormPerson> loadFromFile(File file) throws IOException, ClassNotFoundException {
        fileSaver.loadPersonsFromFile(file);
        personList.loadPersons(fileSaver.getResult());
        return getFormPersonList();
    }

    public List<FormPerson> getFormPersonList() {
        List<Person> persons = personList.getUpdatedPersonList();
        List<FormPerson> formPersons = PersonControllerHelper.personListToFormPersonList(persons);
        return formPersons;
    }

    public void disconnectDatabase() throws SQLException {
        new DatabaseAcces().disconnect();
    }

    public boolean isTableChanged() {
        return personList.hasNextChange();
    }

    public boolean arePersonsDeleted() {
        return personList.arePersonsDeleted();
    }
}
