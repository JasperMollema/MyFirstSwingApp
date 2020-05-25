package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestDatabase {
    @Test
    void testConnect() throws Exception{
        Database database = new Database();

        database.addPerson(new Person("Jasper", "Programmeur", AgeCategory.ADULT, MaritalStatus.COHABITING, false, null, Gender.MALE));
        database.addPerson(new Person("Sara", "Psycholoog", AgeCategory.ADULT, MaritalStatus.COHABITING, false, null, Gender.FEMALE));

        database.connect();
        database.save();

        assertTrue(true);
    }

    @Test
    void testDisconnect() {}
}
