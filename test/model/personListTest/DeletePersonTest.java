package model.personListTest;

import model.Person;
import model.personList.DeletePerson;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeletePersonTest extends PersonChangeTest{
    @Test
    void whenDeletingPersonThenPersonListShouldNotChange() {
        DeletePerson deletePerson = createDeletePerson();
        deletePerson.changePersonList(personList);
        assertTrue(personListIsNotChanged());
    }

    @Test
    void whenDeletingPersonThenThePersonShouldBeDeletedFromReturnedList() {
        DeletePerson deletePerson = createDeletePerson();
        List<Person> returnedList = deletePerson.changePersonList(personList);
        assertFalse(returnedList.contains(person));
    }

    @Test
    void whenDeletingPersonThenReturnedListShouldContainOnePersonLessThanOriginalList() {
        DeletePerson deletePerson = createDeletePerson();
        List<Person> returnedList = deletePerson.changePersonList(personList);
        int differentSize = personList.size() - returnedList.size();
        assertTrue(differentSize == 1);
    }

    private DeletePerson createDeletePerson() {
        return new DeletePerson(person, INDEX);
    }
}
