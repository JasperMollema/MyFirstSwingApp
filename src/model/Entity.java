package model;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class Entity extends DatabaseAcces implements Serializable {
    protected static final long serialVersionUID = 1L;
    protected String tableName;

    public void save() throws SQLException {
        connect();
        if (isNew()) {
            insert();
        }
        update();
        disconnect();
    }

    public void retrieve(int id) throws SQLException {
        connect();
        String retrieveSql = "select * from " + tableName + "where id = ?";
        PreparedStatement retrieveStatement = createPreparedStatement(retrieveSql);
        retrieveStatement.setInt(1, id);
        executeQuery(retrieveStatement);
        disconnect();
    }

    protected Integer determineId() throws SQLException{
        Integer id = findMaxId();

        return ++id;
    }

    protected void insert() throws SQLException {
        executeUpdate(createInsertStatement());
    }

    protected void update() throws SQLException {
        executeUpdate(createUpdateStatement());
    }

    protected Integer findMaxId() throws SQLException {
        String findMaxId = "select max(id) from " + tableName;
        executeQuery(createPreparedStatement(findMaxId));
        resultSet.next();
        return resultSet.getInt(1);
    }

    protected abstract PreparedStatement createInsertStatement() throws SQLException;

    protected abstract PreparedStatement createUpdateStatement() throws SQLException;

    protected abstract boolean isNew();
}
