package model;

import java.sql.*;

/**
 * This class is the parent class for all classes using the database. It's responsible for accessing the database.
 * Child classes can use this class to execute queries and use the resulting {@link ResultSet};
 * */
public class DatabaseAcces {
    private String connectUrl = "jdbc:mariadb://localhost:3306/swingtest";
    private Connection connection;
    protected ResultSet resultSet;

    protected void executeUpdate(PreparedStatement updateStatement) throws SQLException {
        System.out.println("Going to execute " + updateStatement);
        int rowsAffected;

        try {
            rowsAffected = updateStatement.executeUpdate();
            updateStatement.close();
        }

        catch (SQLException sqlException) {
            printSQLException(sqlException);
            throw sqlException;
        }

        System.out.println("Rows affected : " + rowsAffected);
    }

    protected void executeQuery(PreparedStatement selectStatement) throws SQLException {
        System.out.println("Going to execute " + selectStatement);

        try {
            resultSet = selectStatement.executeQuery();
            selectStatement.close();
        }

        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        System.out.println("Query succeeded");
    }

    protected PreparedStatement createPreparedStatement(String sqlString) throws SQLException {
        try {
            connect();
            return connection.prepareStatement(sqlString);
        }

        catch (SQLException sqlException) {
            printSQLException(sqlException);
            throw sqlException;
        }
    }

    protected void connect() throws SQLException {
        if (connection != null) {
            return;
        }
        try {
            connection = DriverManager.getConnection(connectUrl, "root", "jasper");
        }

        catch (SQLException sqlException) {
            printSQLException(sqlException);
            throw sqlException;
        }

        System.out.println("Connected to database");
    }

    protected void disconnect() throws SQLException {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            }

            catch (SQLException sqlException) {
                printSQLException(sqlException);
                throw  sqlException;
            }
        }

        System.out.println("Database disconnected");
    }

    private void printSQLException(SQLException sqlException) {
        System.out.println(sqlException.getMessage());
        System.out.println(sqlException.getSQLState());
        System.out.println(sqlException.getErrorCode());
    }

    public ResultSet getResultSet() {
        return resultSet;
    }
}
