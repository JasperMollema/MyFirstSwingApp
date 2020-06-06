package model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SearchList extends DatabaseAcces{
    protected String tableName;

    public void find() throws SQLException {
        PreparedStatement selectStatement = createPreparedStatement(
                "select * from " + tableName);

        resultSet = selectStatement.executeQuery();
    }

    public void delete() throws SQLException {
        connect();
        PreparedStatement deleteStatement = createPreparedStatement(
                "delete from " + tableName);
        executeUpdate(deleteStatement);
        disconnect();
    }

    public void delete(int id) throws SQLException {
        connect();
        PreparedStatement deleteStatement = createPreparedStatement(
                "delete from " + tableName + " where id = ?");
        deleteStatement.setInt(1, id);
        executeUpdate(deleteStatement);
        disconnect();
    }
}
