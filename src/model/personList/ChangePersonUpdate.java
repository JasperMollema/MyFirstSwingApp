package model.personList;

import model.Person;

public class ChangePersonUpdate extends PersonListChange {
    private Person updatedPerson;

    public ChangePersonUpdate(int index, Person updatedPerson) {
        super(index);
        this.updatedPerson = updatedPerson;
    }

    @Override
    protected void handleChange() {
        personListCopy.set(index, updatedPerson);
    }
}
