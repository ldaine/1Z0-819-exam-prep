package com.daineit.javase11.databasesjdbc.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.daineit.javase11.databasesjdbc.DBConfiguration;

public abstract class Repository<T> {
    protected final String table;
    protected final DBConfiguration dbConfig;

    public Repository(String tableName) {
        this.dbConfig = DBConfiguration.getInstance();
        this.table = tableName;
    }

    protected final Connection getDbConnection() throws SQLException {
        return DriverManager.getConnection(this.dbConfig.getUrl(), this.dbConfig.getProperties());
    }

    public abstract List<T> getAll() throws SQLException;

    public abstract void add(T item) throws SQLException;

    public abstract void update(T item) throws SQLException;

    public void deleteById(int id) throws SQLException {
        String sql = String.format("DELETE FROM %s WHERE id=?", this.table);
        try (Connection conn = this.getDbConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);
            // int numberOfUpdatedRows = statement.executeUpdate();
            // execute() return only if the statement was executet or not 
            // execute is used if you want to execute multiply queries at time. 
            // to get number of lines: 
            // int lines = statement.getUpdateCount();
            boolean success = statement.execute();
            if (success) {
                System.out.println("Items deleted");
            }
        }
    }
}
