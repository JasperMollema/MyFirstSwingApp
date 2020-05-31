package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestDatabase {


    @Test
    void testConnect() throws Exception{
        Database database = new Database();

        database.addPerson(new Person("Jasper", "Software Engineer", AgeCategory.ADULT, MaritalStatus.COHABITING, false, null, Gender.MALE));
        database.addPerson(new Person("Sara", "Student", AgeCategory.ADULT, MaritalStatus.COHABITING, false, null, Gender.FEMALE));

        database.connect();
        database.save();
        database.load();

        assertTrue(true);
    }

    @Test
    void testDisconnect() {}
}
