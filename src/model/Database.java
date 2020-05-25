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

    public void save() throws SQLException {
        String checkSQL = "Select count(*) as count from person where id=?";
        PreparedStatement checkPerson = connection.prepareStatement(checkSQL);

        for (Person person : personList) {
            int id = person.getId();

            checkPerson.setInt(1, id);

            resultSet = checkPerson.executeQuery();
            resultSet.next();

            int count = resultSet.getInt(1);

            System.out.println("Count for person with ID " + id + " is " + count);
        }

        checkPerson.close();
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
        }
    }
}
