package model;

import java.io.*;
import java.sql.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Database {
    private List<Person> personList;
    private String connectUrl = "jdbc:mariadb://localhost:3306/swingtest";
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public Database() {
        personList = new LinkedList<>();
    }

    public void addPerson(Person person) {
        personList.add(person);
    }

    public void deletePerson(int row) {
        personList.remove(row);
    }

    public void clear() {
        personList.clear();
    }

    public List<Person> getPersonList() {
        return Collections.unmodifiableList(personList);
    }

    public void savePersonsToFile(File file) throws IOException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(file))
        )) {
            for (Person person : personList) {
                objectOutputStream.writeObject(person);
            }
        }
    }

    public void loadPersonsFromFile(File file) throws IOException, ClassNotFoundException{

        try (ObjectInputStream inputStream = new ObjectInputStream
                (new BufferedInputStream(new FileInputStream(file)))) {
            while (true) {
                Object object = inputStream.readObject();
                if (object instanceof Person) {
                    personList.add((Person)object);
                }
            }
        } catch (EOFException eofException) {
            // Reached the end of the file.
        }
    }

    public void load() throws SQLException {
        personList.clear();

        String selectSql = "select id, name, occupation, age_category, marital_status," +
                                "is_club_member, member_id, gender from person order by name";
        Statement selectStatement = connection.createStatement();

        ResultSet resultSet = selectStatement.executeQuery(selectSql);

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String occupation = resultSet.getString("occupation");
            String ageCategory = resultSet.getString("age_category");
            String maritalStatus = resultSet.getString("marital_status");
            int isClubMember = resultSet.getInt("is_club_member");
            String memberId = resultSet.getString("member_id");
            String gender = resultSet.getString("gender");

            personList.add(new Person(id, name, occupation, AgeCategory.getAgeCategory(ageCategory),
                    MaritalStatus.getMaritalStatus(maritalStatus), Utils.convertIntToBoolean(isClubMember),
                    memberId, Gender.getGender(gender)));
        }

        selectStatement.close();
        resultSet.close();
    }

    public void save() throws SQLException {
        String checkSql = "Select count(*) as count from person where id=?";
        PreparedStatement checkStatement = connection.prepareStatement(checkSql);

        String insertSql = "insert into person (id, name, occupation, age_category, marital_status, " +
                "is_club_member, member_id, gender) values (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement insertStatement = connection.prepareStatement(insertSql);

        String updateSql = "update person set name=?, occupation=?, age_category=?, marital_status=?, " +
                "is_club_member=?, member_id=?, gender=? where id=?";
        PreparedStatement updateStatement = connection.prepareStatement(updateSql);

        for (Person person : personList) {
            int id = person.getId();
            String name = person.getName();
            String occupation = person.getOccupation();
            AgeCategory ageCategory = person.getAgeCategory();
            MaritalStatus maritalStatus = person.getMaritalStatus();
            int isClubMember = Utils.convertBooleanToInteger(person.getIsClubMember());
            String memberID = person.getMemberID();
            Gender gender = person.getGender();

            checkStatement.setInt(1, id);

            resultSet = checkStatement.executeQuery();
            resultSet.next();

            int count = resultSet.getInt(1);

            if (count == 0) {
                System.out.println("Inserting person with ID " + id);

                int column = 1;
                insertStatement.setInt(column++, id);
                insertStatement.setString(column++, name);
                insertStatement.setString(column++, occupation);
                insertStatement.setString(column++, ageCategory.name());
                insertStatement.setString(column++, maritalStatus.name());
                insertStatement.setInt(column++, isClubMember);
                insertStatement.setString(column++, memberID);
                insertStatement.setString(column++, gender.name());

                System.out.println("Going to execute query " + insertStatement);
                int result = insertStatement.executeUpdate();
                System.out.println("Rows affected : " + result);
            }

            else {
                System.out.println("Updating person with ID " + id);

                int column = 1;
                updateStatement.setString(column++, name);
                updateStatement.setString(column++, occupation);
                updateStatement.setString(column++, ageCategory.name());
                updateStatement.setString(column++, maritalStatus.name());
                updateStatement.setInt(column++, isClubMember);
                updateStatement.setString(column++, memberID);
                updateStatement.setString(column++, gender.name());
                updateStatement.setInt(column++, id);

                System.out.println("Going to execute query " + updateStatement);
                int result = updateStatement.executeUpdate();
                System.out.println("Rows affected : " + result);
            }
        }

        checkStatement.close();
        insertStatement.close();
        updateStatement.close();
    }

    public void connect() throws SQLException {
        // Try with resources:
//        try(Connection connection = DriverManager.getConnection(connectUrl, "root", "jasper");
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery("select * from books")) {
//        }
//
//        catch (SQLException exception) {
//            System.out.println(exception.getMessage());
//            System.out.println(exception.getSQLState());
//            System.out.println(exception.getErrorCode());
//        }

        // Following tutorial:
        try {
            connection = DriverManager.getConnection(connectUrl, "root", "jasper");
            statement = connection.createStatement();
            resultSet = statement.getResultSet();
            System.out.println("Connected to database");
        }

        catch (SQLException exception) {
            System.out.println(exception.getMessage());
            System.out.println(exception.getSQLState());
            System.out.println(exception.getErrorCode());
        }
    }

    public void disconnect() throws SQLException{
        if (connection != null) {
            connection.close();
            System.out.println("Database disconnected");
        }
    }
}
