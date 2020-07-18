package model.personList;

import model.Person;

import java.util.ArrayList;
import java.util.List;

public abstract class PersonListChange {
    protected int index;
    protected List<Person> personListCopy;

    public PersonListChange(int index) {
        this.index = index;
    }

    public List<Person> changePersonList(List<Person> personList) {
        personListCopy = new ArrayList<>(personList);
        handleChange();
        return personListCopy;
    }

    protected abstract void handleChange();
}
