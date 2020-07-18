package model.personList;

public class DeletePersonUpdate extends PersonListChange {
    public DeletePersonUpdate(int index) {
        super(index);
    }

    @Override
    protected void handleChange() {
        personListCopy.remove(index);
    }
}
