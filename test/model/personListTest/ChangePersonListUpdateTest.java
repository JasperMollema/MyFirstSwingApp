package model.personListTest;

import model.AgeCategory;
import model.Gender;
import model.MaritalStatus;
import model.Person;
import model.personList.ChangePersonUpdate;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChangePersonListUpdateTest extends PersonChangeTest {
    private Person updatedPerson;
    private final String OTHER_NAME = "otherName";

    @Test
    void whenUpdatingPersonThenPersonListShouldNotChange() {
        ChangePersonUpdate changePersonUpdate = createUpdatePerson();
        changePersonUpdate.changePersonList(personList);
        assertTrue(personListIsNotChanged());
    }

    @Test
    void whenUpdatingPersonNameThenThePersonNameFromReturnedListShouldBeChanged() {
        ChangePersonUpdate changePersonUpdate = createUpdatePerson();
        List<Person> returnedList = changePersonUpdate.changePersonList(personList);
        updatedPerson = returnedList.get(INDEX);
        assertTrue(nameIsChanged());
    }

    @Test
    void whenUpdatingPersonThenSizeReturnedListAndOriginalListShouldBeTheSame() {
        ChangePersonUpdate changePersonUpdate = createUpdatePerson();
        List<Person> returnedList = changePersonUpdate.changePersonList(personList);
        updatedPerson = returnedList.get(INDEX);
        assertTrue(returnedList.size() == personList.size());
    }

    private ChangePersonUpdate createUpdatePerson() {
        return new ChangePersonUpdate(INDEX, createUpdatedPerson());
    }

    private boolean nameIsChanged() {
        return updatedPerson.getName().equals(OTHER_NAME);
    }

    private Person createUpdatedPerson() {
        return new Person(
                1,
                OTHER_NAME,
                "job",
                AgeCategory.ADULT,
                MaritalStatus.COHABITING,
                false,
                "memberId",
                Gender.MALE
        );
    }
}
