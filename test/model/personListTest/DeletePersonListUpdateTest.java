package model.personListTest;

import model.Person;
import model.personList.DeletePersonUpdate;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeletePersonListUpdateTest extends PersonChangeTest{
    @Test
    void whenDeletingPersonThenPersonListShouldNotChange() {
        DeletePersonUpdate deletePersonUpdate = createDeletePerson();
        deletePersonUpdate.changePersonList(personList);
        assertTrue(personListIsNotChanged());
    }

    @Test
    void whenDeletingPersonThenThePersonShouldBeDeletedFromReturnedList() {
        DeletePersonUpdate deletePersonUpdate = createDeletePerson();
        List<Person> returnedList = deletePersonUpdate.changePersonList(personList);
        assertFalse(returnedList.contains(person));
    }

    @Test
    void whenDeletingPersonThenReturnedListShouldContainOnePersonLessThanOriginalList() {
        DeletePersonUpdate deletePersonUpdate = createDeletePerson();
        List<Person> returnedList = deletePersonUpdate.changePersonList(personList);
        int differentSize = personList.size() - returnedList.size();
        assertTrue(differentSize == 1);
    }

    private DeletePersonUpdate createDeletePerson() {
        return new DeletePersonUpdate(INDEX);
    }
}
