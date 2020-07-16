package model.personList;

import model.Person;

import java.util.ArrayList;
import java.util.List;

public abstract class PersonListChange implements ListUpdate{
    protected Person person;
    protected int index;
    protected List<Person> personListCopy;

    public PersonListChange(Person person, int index) {
        this.person = person;
        this.index = index;
    }

    public List<Person> changePersonList(List<Person> personList) {
        personListCopy = new ArrayList<>(personList);
        handleChange();
        return personListCopy;
    }

    public abstract void handleChange();

    @Override
    public int getIndex() {
        return index;
    }
}
