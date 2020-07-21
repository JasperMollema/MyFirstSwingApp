package model.personList;

import model.Person;

import java.util.List;

public class PersonList {
    private ListUpdateNavigator<Person> personListUpdateNavigator;

    public PersonList() {
        personListUpdateNavigator = new ListUpdateNavigator();
    }

    public void addPerson(Person person) {
        addPersonListChange(new AddPersonUpdate(person, -1));
    }

    public void changePerson(int index, Person changedPerson){
        addPersonListChange(new ChangePersonUpdate(index, changedPerson));
    }

    public void deletePerson(int index) {
        addPersonListChange(new DeletePersonUpdate(index));
    }

    private void addPersonListChange(PersonListChange personListChange) {
        List<Person> currentList = personListUpdateNavigator.getCurrentUpdate();
        List<Person> updatedList = personListChange.changePersonList(currentList);
        PersonListUpdate listUpdate = new PersonListUpdate(updatedList);
        personListUpdateNavigator.addTableUpdate(listUpdate);
    }

    public boolean arePersonsDeleted() {return true;}

    public void undoLastChange(){
        personListUpdateNavigator.goToPreviousUpdate();
    }

    public void redoLastChange(){
        personListUpdateNavigator.goToNextUpdate();
    }

    public boolean hasPreviousChange(){
        return personListUpdateNavigator.hasPreviousUpdate();
    }

    public boolean hasNextChange(){
        return personListUpdateNavigator.hasNextUpdate();
    }

    public void loadPersons(List<Person> persons) {
        personListUpdateNavigator.addTableUpdate(new PersonListUpdate(persons));
    }

    public void clear() {
        personListUpdateNavigator.removeAllUpdates();
    }

    public List<Person> getUpdatedPersonList() {
        return personListUpdateNavigator.getCurrentUpdate();}
}
