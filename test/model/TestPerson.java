package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestPerson {
    @Test
    void testEquals() {
        Person person = new Person(1, "name", "occupation", AgeCategory.ADULT, MaritalStatus.COHABITING, true, "1", Gender.MALE );
        Person samePerson = new Person(1, "diffName", "noOccupation", AgeCategory.ADULT, MaritalStatus.COHABITING, true, "1", Gender.MALE );
        Person differentPerson = new Person(2, "name", "occupation", AgeCategory.ADULT, MaritalStatus.COHABITING, true, "1", Gender.MALE );
        Person nullIdPerson = new Person(null, "name", "occupation", AgeCategory.ADULT, MaritalStatus.COHABITING, true, "1", Gender.MALE );

        assertTrue(person.equals(samePerson));
        assertFalse(person.equals(differentPerson));
        assertFalse(person.equals(nullIdPerson));
    }
}
