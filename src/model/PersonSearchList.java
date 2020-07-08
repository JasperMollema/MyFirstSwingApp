package model;

import utils.Utils;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PersonSearchList extends SearchList {

    /** This list backs the data shown on the screen. Should always be up to date. */
    private List<Person> personList;

    private List<Person> deletedPersons;
    private List<Person> updatedPersons;
    private List<Person> addedPersons;
    private PersonUpdateManager personUpdateManager;

    private static final String ID_COLUMN = "id";
    private static final String NAME_COLUMN = "name";
    private static final String OCCUPATION_COLUMN = "occupation";
    private static final String AGE_CATEGORY_COLUMN = "age_category";
    private static final String MARITAL_STATUS_COLUMN = "marital_status";
    private static final String IS_CLUB_MEMBER_COLUMN = "is_club_member";
    private static final String MEMBER_ID_COLUMN = "member_id";
    private static final String GENDER_COLUMN = "gender";

    public PersonSearchList() {
        super();
        personList = new ArrayList<>();
        deletedPersons = new ArrayList<>();
        updatedPersons = new ArrayList<>();
        addedPersons = new ArrayList<>();
        personUpdateManager = new PersonUpdateManager();
        this.tableName = "person";
    }

    public void addPerson(Person person) {
        personList.add(person);
    }

    public void deletePerson(int index) {
        System.out.println("PersonSearchList deletePerson()");
        Person person = personList.get(index);
        personList.remove(index);
        deletedPersons.add(person);
    }

    public void save() throws SQLException {
        System.out.println("PersonSearchList save()");
        for (Person person : addedPersons) {
            person.save();
        }

        for (Person person : deletedPersons) {
            delete(person.getId());
        }

        for (Person person : updatedPersons) {
            person.update();
        }

        personList.clear();
        deletedPersons.clear();
        addedPersons.clear();
        updatedPersons.clear();
    }

    public void findPersons() throws SQLException {
        System.out.println("PersonSearchList findPersons()");
        personList.clear();
        find();

        while (result.next()) {
            Integer id = result.getInt(ID_COLUMN);
            String name = result.getString(NAME_COLUMN);
            String occupation = result.getString(OCCUPATION_COLUMN);
            String ageCategory = result.getString(AGE_CATEGORY_COLUMN);
            String maritalStatus = result.getString(MARITAL_STATUS_COLUMN);
            int isClubMember = result.getInt(IS_CLUB_MEMBER_COLUMN);
            String memberId = result.getString(MEMBER_ID_COLUMN);
            String gender = result.getString(GENDER_COLUMN);

            Person person = new Person(id, name, occupation, AgeCategory.getAgeCategory(ageCategory),
                    MaritalStatus.getMaritalStatus(maritalStatus), Utils.integerToBoolean(isClubMember),
                    memberId, Gender.getGender(gender));
            personList.add(person);
        }

        result.close();
    }

    public void update(int index, Person updatedPerson) {
        // The updated person created in controller does not have an id:
        updatedPerson.setId(personList.get(index).getId());
        personUpdateManager.addPersonUpdate(new PersonUpdate(index, personList.get(index), updatedPerson));
        updatedPersons.add(updatedPerson);
        personList.set(index, updatedPerson);
    }

    public void goToPreviousUpdate() {
        PersonUpdate previousUpdate = personUpdateManager.getPreviousUpdate();
        int index = previousUpdate.getIndex();
        Person originalPerson = previousUpdate.getOriginalPerson();
        personList.set(index, originalPerson);
    }

    public void goToNextUpdate() {
        PersonUpdate nextUpdate = personUpdateManager.getNextUpdate();
        int index = nextUpdate.getIndex();
        Person updatedPerson = nextUpdate.getUpdatedPerson();
        personList.set(index, updatedPerson);
    }

    public boolean hasPreviousUpdate() {
        return personUpdateManager.hasPreviousUpdate();
    }

    public boolean hasNextUpdate() {
        return personUpdateManager.hasNextUpdate();
    }

    public String translateIdToName (Integer id) throws SQLException {
        if (id == null) {
            return null;
        }

        String selectSql = "select * from " + tableName + " where id =?";
        PreparedStatement selectStatement = database.createPreparedStatement(selectSql);
        selectStatement.setInt(1, id);

        database.executeQuery(selectStatement);

        if (!result.next()) {
            return null;
        }

        return result.getString(NAME_COLUMN);
    }

    public List<Person> getPersons() {
        return Collections.unmodifiableList(personList);
    }
}
