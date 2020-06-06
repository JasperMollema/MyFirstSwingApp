package model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PersonSearchList extends SearchList {
    private List<Person> personList;

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
        this.tableName = "person";
    }


    public List<Person> findPersons() throws SQLException {
        connect();
        find();

        while (resultSet.next()) {
            Integer id = resultSet.getInt(ID_COLUMN);
            String name = resultSet.getString(NAME_COLUMN);
            String occupation = resultSet.getString(OCCUPATION_COLUMN);
            String ageCategory = resultSet.getString(AGE_CATEGORY_COLUMN);
            String maritalStatus = resultSet.getString(MARITAL_STATUS_COLUMN);
            int isClubMember = resultSet.getInt(IS_CLUB_MEMBER_COLUMN);
            String memberId = resultSet.getString(MEMBER_ID_COLUMN);
            String gender = resultSet.getString(GENDER_COLUMN);

            Person person = new Person(id, name, occupation, AgeCategory.getAgeCategory(ageCategory),
                    MaritalStatus.getMaritalStatus(maritalStatus), Utils.convertIntToBoolean(isClubMember),
                    memberId, Gender.getGender(gender));
            personList.add(person);
        }

        resultSet.close();
        disconnect();
        return Collections.unmodifiableList(personList);
    }

    public String translateIdToName (Integer id) throws SQLException {
        if (id == null) {
            return null;
        }

        String selectSql = "select * from " + tableName + " where id =?";
        PreparedStatement selectStatement = createPreparedStatement(selectSql);
        selectStatement.setInt(1, id);

        executeQuery(selectStatement);

        if (!resultSet.next()) {
            return null;
        }

        return resultSet.getString(NAME_COLUMN);
    }
}
