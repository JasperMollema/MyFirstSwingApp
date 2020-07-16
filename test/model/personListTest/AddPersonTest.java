package model.personListTest;

import model.AgeCategory;
import model.Gender;
import model.MaritalStatus;
import model.Person;
import model.personList.AddPerson;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddPersonTest extends PersonChangeTest {
    private Person addedPerson;

    @Test
    void whenAddingPersonThenPersonListShouldNotChange() {
        AddPerson addPerson = createAddPerson();
        addPerson.changePersonList(personList);
        assertTrue(personListIsNotChanged());
    }

    @Test
    void whenAddingPersonThenTheReturnedListShouldContainAddedPerson() {
        AddPerson addPerson = createAddPerson();
        List<Person> returnedList = addPerson.changePersonList(personList);
        assertTrue(returnedList.contains(addedPerson));
    }

    private AddPerson createAddPerson() {
        createAddedPerson();
        return new AddPerson(addedPerson, INDEX);
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
