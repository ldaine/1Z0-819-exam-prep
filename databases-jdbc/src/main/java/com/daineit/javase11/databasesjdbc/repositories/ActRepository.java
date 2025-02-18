package com.daineit.javase11.databasesjdbc.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.daineit.javase11.databasesjdbc.models.Act;

public class ActRepository extends Repository<Act> {

    public ActRepository(Connection conn) {
        super(conn, "acts");
    }

    public List<Act> getAll() throws SQLException {
        List<Act> result = new ArrayList<>();
        String sql = "SELECT id, name, recordLabel FROM " + this.table;
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                String recordlabel = rs.getString("recordLabel");
                result.add(new Act(id, name, recordlabel));
            }
        }

        return result;
    }

    @Override
    public void add(Act item) throws SQLException {
        String sql = String.format("INSERT INTO %s (name, recordLabel) values (%s,%s) ", super.table, item.getName(), item.getRecordLabel());
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            int numberOfUpdatedRows = statement.executeUpdate();
            if(numberOfUpdatedRows > 0){
                return;
            } else {
                throw new SQLException("Could not add item");
            }
        }
    }
}
