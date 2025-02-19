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
        String sql = String.format("DELETE FROM %s WHERE id=%d", this.table, id);
        try (Connection conn = this.getDbConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {
            int numberOfUpdatedRows = statement.executeUpdate();
            if (numberOfUpdatedRows == 0) {
                System.out.println("Items deleted");
            }
        }
    }

    protected void simpleInsertWithExecuteUpdate(String sql) throws SQLException {
        try (Connection conn = this.getDbConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {
            int numberOfUpdatedRows = statement.executeUpdate();
            if (numberOfUpdatedRows > 0) {
                System.out.println("Database updated");
                return;
            } else {
                throw new SQLException("Update failed");
            }
        }
    }
}
