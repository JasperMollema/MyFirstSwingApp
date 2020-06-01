package model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PersonSearchList extends SearchList{
    private List<Person> personList;
    private static final String TABLE_NAME = "person";

    private static final String ID_COLUMN = "id";
    private static final String NAME_COLUMN = "name";
    private static final String OCCUPATION_COLUMN = "occupation";
    private static final String AGE_CATEGORY_COLUMN = "age_category";
    private static final String MARITAL_STATUS_COLUMN = "marital_status";
    private static final String IS_CLUB_MEMBER_COLUMN = "is_club_member";
    private static final String MEMBER_ID_COLUMN = "member_id";
    private static final String GENDER_COLUMN = "gender";

    public PersonSearchList() {
        personList = new ArrayList<>();
    }

    public void addPerson(Person person) {
        personList.add(person);
    }

    public void savePersons () throws SQLException {
        connect();

        for (Person person : personList) {
            if (personExists(person.getId())) {
                updatePerson(person);
            }

            else {
                insertPerson(person);
            }
        }

        disconnect();
    }

    public List<Person> retrievePersons() throws SQLException {
        connect();
        personList.clear();

        retrieve(TABLE_NAME);

        while (resultSet.next()) {
            int id = resultSet.getInt(ID_COLUMN);
            String name = resultSet.getString(NAME_COLUMN);
            String occupation = resultSet.getString(OCCUPATION_COLUMN);
            String ageCategory = resultSet.getString(AGE_CATEGORY_COLUMN);
            String maritalStatus = resultSet.getString(MARITAL_STATUS_COLUMN);
            int isClubMember = resultSet.getInt(IS_CLUB_MEMBER_COLUMN);
            String memberId = resultSet.getString(MEMBER_ID_COLUMN);
            String gender = resultSet.getString(GENDER_COLUMN);

            personList.add(new Person(id, name, occupation, AgeCategory.getAgeCategory(ageCategory),
                    MaritalStatus.getMaritalStatus(maritalStatus), Utils.convertIntToBoolean(isClubMember),
                    memberId, Gender.getGender(gender)));
        }

        resultSet.close();
        disconnect();

        return Collections.unmodifiableList(personList);
    }

    private boolean personExists(int id) throws SQLException {
        String selectSql = "select * from " + TABLE_NAME + " where id =?";
        PreparedStatement selectStatement = createPreparedStatement(selectSql);

        selectStatement.setInt(1, id);

        executeQuery(selectStatement);
        boolean personExists = resultSet.next();

        selectStatement.close();
        resultSet.close();

        return personExists;
    }

    private void updatePerson(Person person) throws SQLException {
        System.out.println("Updating person with ID " + person.getId());
        String updateSql =
                "update person set " +
                "name=?, " +
                "occupation=?, " +
                "age_category=?, " +
                "marital_status=?, " +
                "is_club_member=?, " +
                "member_id=?, " +
                "gender=? " +
                "where id=?";

        PreparedStatement updateStatement = createPreparedStatement(updateSql);
        int column = 1;

        updateStatement.setString(column++, person.getName());
        updateStatement.setString(column++, person.getOccupation());
        updateStatement.setString(column++, person.getAgeCategory().name());
        updateStatement.setString(column++, person.getMaritalStatus().name());
        updateStatement.setInt(column++, Utils.convertBooleanToInteger(person.getIsClubMember()));
        updateStatement.setString(column++, person.getMemberID());
        updateStatement.setString(column++, person.getGender().name());
        updateStatement.setInt(column++, person.getId());

        executeUpdate(updateStatement);
        updateStatement.close();
    }

    private void insertPerson(Person person) throws SQLException {
        System.out.println("Going to insert person with ID " + person.getId());

        String insertSql = "insert into " + TABLE_NAME +
                "(id, " +
                "name, " +
                "occupation, " +
                "age_category, " +
                "marital_status, " +
                "is_club_member, " +
                "member_id, " +
                "gender) " +
                "values" +
                "(?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement insertStatement = createPreparedStatement(insertSql);
        int column = 1;

        insertStatement.setInt(column++, person.getId());
        insertStatement.setString(column++, person.getName());
        insertStatement.setString(column++, person.getOccupation());
        insertStatement.setString(column++, person.getAgeCategory().name());
        insertStatement.setString(column++, person.getMaritalStatus().name());
        insertStatement.setInt(column++, Utils.convertBooleanToInteger(person.getIsClubMember()));
        insertStatement.setString(column++, person.getMemberID());
        insertStatement.setString(column++, person.getGender().name());

        executeUpdate(insertStatement);
        insertStatement.close();
    }
}
