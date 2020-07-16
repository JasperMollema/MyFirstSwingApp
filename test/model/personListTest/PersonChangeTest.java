package model.personListTest;

import model.AgeCategory;
import model.Gender;
import model.MaritalStatus;
import model.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonChangeTest {
    protected Person person = createPerson();
    protected final int INDEX = 0;
    protected List<Person> personList = createInitialPersonList();

    protected Person createPerson() {
        return new Person(
                1,
                "name",
                "job",
                AgeCategory.ADULT,
                MaritalStatus.COHABITING,
                false,
                "memberId",
                Gender.MALE
        );
    }

    protected List<Person> createInitialPersonList() {
        List<Person> initialPersonList = new ArrayList<>();
        initialPersonList.add(person);
        initialPersonList.add(new Person());
        return initialPersonList;
    }

    protected boolean personListIsNotChanged() {
        List<Person> originalPersonList = createInitialPersonList();
        return originalPersonList.equals(personList);
    }
}
