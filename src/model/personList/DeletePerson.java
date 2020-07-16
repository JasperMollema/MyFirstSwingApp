package model.personList;

import model.Person;

public class DeletePerson extends PersonListChange {
    public DeletePerson(Person person, int index) {
        super(person, index);
    }

    @Override
    public void handleChange() {
        personListCopy.remove(person);
    }
}
