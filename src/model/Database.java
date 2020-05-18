package model;

import java.util.ArrayList;
import java.util.List;

public class Database {
    private List<Person> personList;

    public Database() {
        personList = new ArrayList<>();
    }

    public void addPerson(Person person) {
        personList.add(person);
        personList.stream().forEach(p -> System.out.println(p));
    }

    public List<Person> getPersonList() {
        return personList;
    }
}
