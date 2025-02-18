package com.daineit.javase11.databasesjdbc.repositories;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class Repository<T> {
    protected final String table;
    protected final Connection connection;

    public Repository(Connection conn, String tableName) {
        this.connection = conn;
        this.table = tableName;
    }

    public abstract List<T> getAll() throws SQLException;

    public abstract void add(T item) throws SQLException;
}
