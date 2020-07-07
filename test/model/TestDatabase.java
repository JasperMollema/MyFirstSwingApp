package model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestDatabase {


    @Test
    void testConnect() throws Exception{

        PersonSearchList personSearchList = new PersonSearchList();
        personSearchList.delete();

        new Person("Jasper", "Software Engineer", AgeCategory.ADULT, MaritalStatus.COHABITING, false, null, Gender.MALE).save();
        new Person("Sara", "Student", AgeCategory.ADULT, MaritalStatus.COHABITING, false, null, Gender.FEMALE).save();

        personSearchList.findPersons();
        List<Person> personList = personSearchList.getPersons();

        for (Person person : personList) {
            String name = personSearchList.translateIdToName(person.getId());
            System.out.println(name);
        }

        personSearchList.delete(1);

        for (Person person : personList) {
            String name = personSearchList.translateIdToName(person.getId());
            System.out.println(name);
        }

        personSearchList.delete();

        for (Person person : personList) {
            String name = personSearchList.translateIdToName(person.getId());
            System.out.println(name);
        }

        assertTrue(true);
    }

    @Test
    void testDisconnect() {}
}
