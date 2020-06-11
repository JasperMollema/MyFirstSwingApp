package model;

import utils.Utils;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PersonSearchList extends SearchList {
    private List<Person> personList;
    private List<Person> deletedPersons;

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
        this.tableName = "person";
    }

    public void addPerson(Person person) {
        personList.add(person);
    }

    public void deletePerson(int index) {
        Person person = personList.get(index);
        personList.remove(index);
        deletedPersons.add(person);
    }

    public void save() throws SQLException {
        for (Person person : personList) {
            person.save();
        }

        for (Person person : deletedPersons) {
            delete(person.getId());
        }

        personList.clear();
        deletedPersons.clear();
    }

    public void findPersons() throws SQLException {
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

    public List<Person> getResult() {
        return Collections.unmodifiableList(personList);
    }
}
