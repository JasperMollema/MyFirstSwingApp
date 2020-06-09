package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchList {
    protected String tableName;
    protected DatabaseAcces database;
    protected ResultSet result;

    public SearchList() {
        database = new DatabaseAcces();
    }

    public void find() throws SQLException {
        PreparedStatement selectStatement = database.createPreparedStatement(
                "select * from " + tableName);

        result = selectStatement.executeQuery();
    }

    public void delete() throws SQLException {
        PreparedStatement deleteStatement = database.createPreparedStatement(
                "delete from " + tableName);
        database.executeUpdate(deleteStatement);
    }

    public void delete(int id) throws SQLException {
        PreparedStatement deleteStatement = database.createPreparedStatement(
                "delete from " + tableName + " where id = ?");
        deleteStatement.setInt(1, id);
        database.executeUpdate(deleteStatement);
    }
}
