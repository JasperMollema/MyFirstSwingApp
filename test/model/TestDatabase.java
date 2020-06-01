package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestDatabase {


    @Test
    void testConnect() throws Exception{
        PersonSearchList personSearchList = new PersonSearchList();

        personSearchList.addPerson(new Person(1,"Jasper", "Software Engineer", AgeCategory.ADULT, MaritalStatus.COHABITING, false, null, Gender.MALE));
        personSearchList.addPerson(new Person(2,"Sara", "Student", AgeCategory.ADULT, MaritalStatus.COHABITING, false, null, Gender.FEMALE));

        personSearchList.savePersons();
        personSearchList.retrievePersons();

        assertTrue(true);
    }

    @Test
    void testDisconnect() {}
}
