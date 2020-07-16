package model.personList;

import model.Person;

public class UpdatePerson extends PersonListChange {
    private Person updatedPerson;

    public UpdatePerson(Person person, int index, Person updatedPerson) {
        super(person, index);
        this.updatedPerson = updatedPerson;
    }

    @Override
    public void handleChange() {
        personListCopy.set(index, updatedPerson);
    }
}
