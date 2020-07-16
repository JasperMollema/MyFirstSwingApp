package model.personList;

import model.Person;

public class AddPerson extends PersonListChange {
    public AddPerson(Person person, int index) {
        super(person, index);
    }

    @Override
    public void handleChange() {
        personListCopy.add(person);
    }
}
