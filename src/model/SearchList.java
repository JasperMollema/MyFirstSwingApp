package model;

import java.sql.*;

public abstract class SearchList {
    private String connectUrl = "jdbc:mariadb://localhost:3306/swingtest";
    private Connection connection;
    protected ResultSet resultSet;

    protected void executeUpdate(PreparedStatement updateStatement) throws SQLException {
        System.out.println("Going to execute " + updateStatement);

        int result = updateStatement.executeUpdate();
        System.out.println("Rows affected : " + result);
    }

    protected void executeQuery(PreparedStatement selectStatement) throws SQLException {
        System.out.println("Going to execute " + selectStatement);

        resultSet = selectStatement.executeQuery();
        System.out.println("Query succeeded");
    }

    protected void retrieve(String tableName) throws SQLException {

        PreparedStatement selectStatement = connection.prepareStatement(
                "select * from " + tableName + ";");

        resultSet = selectStatement.executeQuery();

        selectStatement.close();
    }

    protected PreparedStatement createPreparedStatement(String sqlString) throws SQLException{
        return connection.prepareStatement(sqlString);
    }

    protected boolean connect() {
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
        }

        catch (SQLException exception) {
            printSQLException(exception);
            return false;
        }

        System.out.println("Connected to database");
        return true;
    }

    protected boolean disconnect() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException exception) {
                printSQLException(exception);
                return false;
            }
        }

        System.out.println("Database disconnected");
        return true;
    }

    private void printSQLException(SQLException sqlException) {
        System.out.println(sqlException.getMessage());
        System.out.println(sqlException.getSQLState());
        System.out.println(sqlException.getErrorCode());
    }
}
