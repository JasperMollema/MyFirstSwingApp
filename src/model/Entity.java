package model;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Entity implements Serializable {
    protected static final long serialVersionUID = 1L;
    protected String tableName;
    protected DatabaseAcces database;
    protected ResultSet result;

    public Entity() {
        database = new DatabaseAcces();
    }

    public void retrieve(int id) throws SQLException {
        String retrieveSql = "select * from " + tableName + "where id = ?";
        PreparedStatement retrieveStatement = database.createPreparedStatement(retrieveSql);
        retrieveStatement.setInt(1, id);
        database.executeQuery(retrieveStatement);
        result = database.getResultSet();
    }

    protected Integer determineId() throws SQLException{
        Integer id = findMaxId();

        return ++id;
    }

    public void save() throws SQLException {
        database.executeUpdate(createInsertStatement());
    }

    public void update() throws SQLException {
        database.executeUpdate(createUpdateStatement());
    }

    protected Integer findMaxId() throws SQLException {
        String findMaxId = "select max(id) from " + tableName;
        database.executeQuery(database.createPreparedStatement(findMaxId));
        result = database.getResultSet();
        result.next();
        return result.getInt(1);
    }

    protected abstract PreparedStatement createInsertStatement() throws SQLException;

    protected abstract PreparedStatement createUpdateStatement() throws SQLException;

    protected abstract boolean isNew();
}
