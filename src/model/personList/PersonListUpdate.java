package model.personList;

import model.Person;

import java.util.List;

public class PersonListUpdate implements ListUpdate<Person> {
    private List<Person> personList;

    public PersonListUpdate(List<Person> personList) {
        this.personList = personList;
    }

    @Override
    public List<Person> getList() {
        return personList;
    }
}
