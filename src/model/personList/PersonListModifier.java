package model.personList;

import model.Person;

import java.util.List;

public class PersonListModifier {
    private List<Person> personList;

    public PersonListModifier(List<Person> personList) {
        this.personList = personList;
    }

    public void addPersonListChange(PersonListChange personListChange) {
        personList = personListChange.changePersonList(personList);
    }

    public void undo() {}

    public void redo() {}

    public List<Person> getPersonList() {
        return personList;
    }
}
