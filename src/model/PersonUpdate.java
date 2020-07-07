package model;

public class PersonUpdate {
    private Person originalPerson;
    private Person updatedPerson;

    public PersonUpdate(Person originalPerson, Person updatedPerson) {
        this.originalPerson = originalPerson;
        this.updatedPerson = updatedPerson;
    }
}
