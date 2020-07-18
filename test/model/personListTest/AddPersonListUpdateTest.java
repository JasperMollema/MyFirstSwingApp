package model.personListTest;

import model.AgeCategory;
import model.Gender;
import model.MaritalStatus;
import model.Person;
import model.personList.AddPersonUpdate;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddPersonListUpdateTest extends PersonChangeTest {
    private Person addedPerson;

    @Test
    void whenAddingPersonThenPersonListShouldNotChange() {
        AddPersonUpdate addPersonUpdate = createAddPerson();
        addPersonUpdate.changePersonList(personList);
        assertTrue(personListIsNotChanged());
    }

    @Test
    void whenAddingPersonThenTheReturnedListShouldContainAddedPerson() {
        AddPersonUpdate addPersonUpdate = createAddPerson();
        List<Person> returnedList = addPersonUpdate.changePersonList(personList);
        assertTrue(returnedList.contains(addedPerson));
    }

    private AddPersonUpdate createAddPerson() {
        createAddedPerson();
        return new AddPersonUpdate(addedPerson, INDEX);
    }

    protected void createAddedPerson() {
        addedPerson = new Person(
                2,
                "name",
                "job",
                AgeCategory.ADULT,
                MaritalStatus.COHABITING,
                false,
                "memberId",
                Gender.MALE
        );
    }
}
