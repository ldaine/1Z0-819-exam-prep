package com.daineit.javase11.databasesjdbc.repositories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.daineit.javase11.databasesjdbc.models.Act;

public class ActRepository {

    private final String table = "acts";
    private final Connection connection;

    public ActRepository(Connection conn) {
        this.connection = conn;
    }

    public List<Act> getAll() throws SQLException {
        List<Act> result = new ArrayList<>();
        String sql = "SELECT id, name, recordLabel FROM " + table;
        var statement = this.connection.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            Integer id = rs.getInt(1);
            String name = rs.getString(2);
            String recordlabel = rs.getString(3);
            result.add(new Act(id, name, recordlabel));
        }

        return result;
    }
}
