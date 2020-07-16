package model.personListTest;

import model.AgeCategory;
import model.Gender;
import model.MaritalStatus;
import model.Person;
import model.personList.UpdatePerson;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UpdatePersonTest extends PersonChangeTest {
    private Person updatedPerson;
    private final String OTHER_NAME = "otherName";

    @Test
    void whenUpdatingPersonThenPersonListShouldNotChange() {
        UpdatePerson updatePerson = createUpdatePerson();
        updatePerson.changePersonList(personList);
        assertTrue(personListIsNotChanged());
    }

    @Test
    void whenUpdatingPersonNameThenThePersonNameFromReturnedListShouldBeChanged() {
        UpdatePerson updatePerson = createUpdatePerson();
        List<Person> returnedList = updatePerson.changePersonList(personList);
        updatedPerson = returnedList.get(INDEX);
        assertTrue(nameIsChanged());
    }

    @Test
    void whenUpdatingPersonThenSizeReturnedListAndOriginalListShouldBeTheSame() {
        UpdatePerson updatePerson = createUpdatePerson();
        List<Person> returnedList = updatePerson.changePersonList(personList);
        updatedPerson = returnedList.get(INDEX);
        assertTrue(returnedList.size() == personList.size());
    }

    private UpdatePerson createUpdatePerson() {
        return new UpdatePerson(person, INDEX, createUpdatedPerson());
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
