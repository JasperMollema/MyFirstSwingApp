package model;

import utils.Utils;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Person extends Entity {

    private static int count = 1;

    private Integer id;
    private String name;
    private String occupation;
    private AgeCategory ageCategory;
    private MaritalStatus maritalStatus;
    private Boolean isClubMember;
    private String memberID;
    private Gender gender;

    private static final String ID_COLUMN = "id";
    private static final String NAME_COLUMN = "name";
    private static final String OCCUPATION_COLUMN = "occupation";
    private static final String AGE_CATEGORY_COLUMN = "age_category";
    private static final String MARITAL_STATUS_COLUMN = "marital_status";
    private static final String IS_CLUB_MEMBER_COLUMN = "is_club_member";
    private static final String MEMBER_ID_COLUMN = "member_id";
    private static final String GENDER_COLUMN = "gender";

    public Person() {
        super();
        tableName = "person";
    }

    public Person(String name, String occupation, AgeCategory ageCategory, MaritalStatus maritalStatus,
                  Boolean isClubMember, String memberID, Gender gender) {
        this();
        this.name = name;
        this.occupation = occupation;
        this.ageCategory = ageCategory;
        this.maritalStatus = maritalStatus;
        this.isClubMember = isClubMember;
        this.memberID = memberID;
        this.gender = gender;
    }

    public Person(Integer id, String name, String occupation, AgeCategory ageCategory, MaritalStatus maritalStatus,
                  Boolean isClubMember, String memberID, Gender gender) {
        this();
        this.id = id;
        this.name = name;
        this.occupation = occupation;
        this.ageCategory = ageCategory;
        this.maritalStatus = maritalStatus;
        this.isClubMember = isClubMember;
        this.memberID = memberID;
        this.gender = gender;
    }

    @Override
    public void retrieve(int id) throws SQLException {
        super.retrieve(id);
        if (!result.next()) {
            return;
        }

        name = result.getString(NAME_COLUMN);
        occupation = result.getString(OCCUPATION_COLUMN);
        ageCategory = AgeCategory.valueOf(result.getString(AGE_CATEGORY_COLUMN));
        maritalStatus = MaritalStatus.valueOf(result.getString(MARITAL_STATUS_COLUMN));
        isClubMember = Utils.integerToBoolean(result.getInt(IS_CLUB_MEMBER_COLUMN));
        memberID = result.getString(MEMBER_ID_COLUMN);
        gender = Gender.valueOf(result.getString(GENDER_COLUMN));

        result.close();
    }

    @Override
    protected PreparedStatement createInsertStatement() throws SQLException {

        String insertSql = "insert into " + tableName +
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

        PreparedStatement insertStatement = database.createPreparedStatement(insertSql);
        int column = 1;

        if (id == null) {
            id = determineId();
        }

        insertStatement.setInt(column++, id);
        insertStatement.setString(column++, name);
        insertStatement.setString(column++, occupation);
        insertStatement.setString(column++, ageCategory.name());
        insertStatement.setString(column++, maritalStatus.name());
        insertStatement.setInt(column++, Utils.booleanToInteger(isClubMember));
        insertStatement.setString(column++, memberID);
        insertStatement.setString(column++, gender.name());

        return insertStatement;
    }

    @Override
    protected PreparedStatement createUpdateStatement() throws SQLException{
        String updateSql =
                "update " + tableName + " set " +
                        "name=?, " +
                        "occupation=?, " +
                        "age_category=?, " +
                        "marital_status=?, " +
                        "is_club_member=?, " +
                        "member_id=?, " +
                        "gender=? " +
                        "where id=?";

        PreparedStatement updateStatement = database.createPreparedStatement(updateSql);
        int column = 1;

        updateStatement.setString(column++, name);
        updateStatement.setString(column++, occupation);
        updateStatement.setString(column++, ageCategory.name());
        updateStatement.setString(column++, maritalStatus.name());
        updateStatement.setInt(column++, Utils.booleanToInteger(isClubMember));
        updateStatement.setString(column++, memberID);
        updateStatement.setString(column++, gender.name());
        updateStatement.setInt(column++, id);

        return updateStatement;
    }

    @Override
    protected boolean isNew() {
        return id == null;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) object;

        return otherPerson.id == this.id;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", occupation='" + occupation + '\'' +
                ", ageCategory=" + ageCategory +
                ", maritalStatus=" + maritalStatus +
                ", isClubMember=" + isClubMember +
                ", memberID='" + memberID + '\'' +
                ", gender=" + gender +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOccupation() {
        return occupation;
    }

    public AgeCategory getAgeCategory() {
        return ageCategory;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public Boolean getIsClubMember() {
        return isClubMember;
    }

    public String getMemberID() {
        return memberID;
    }

    public Gender getGender() {
        return gender;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
