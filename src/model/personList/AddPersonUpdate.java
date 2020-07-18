package model.personList;

import model.Person;

public class AddPersonUpdate extends PersonListChange {
    private Person personToBeAdded;
    public AddPersonUpdate(Person person, int index) {
        super(index);
        personToBeAdded = person;
    }

    @Override
    protected void handleChange() {
        personListCopy.add(personToBeAdded);
    }
}
