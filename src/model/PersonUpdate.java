package model;

public class PersonUpdate {
    private int index;
    private Person originalPerson;
    private Person updatedPerson;

    public PersonUpdate(int index, Person originalPerson, Person updatedPerson) {
        this.index = index;
        this.originalPerson = originalPerson;
        this.updatedPerson = updatedPerson;
    }

    public int getIndex() {
        return index;
    }

    public Person getOriginalPerson() {
        return originalPerson;
    }

    public Person getUpdatedPerson() {
        return updatedPerson;
    }
}
