package com.daineit.javase11.databasesjdbc.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.daineit.javase11.databasesjdbc.models.Act;

public class ActRepository extends Repository<Act> {

    public ActRepository() {
        super("acts");
    }

    public List<Act> getAll() throws SQLException {
        List<Act> result = new ArrayList<>();
        String sql = "SELECT id, name, recordLabel FROM " + this.table;
        try (Connection conn = super.getDbConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {
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
        String sql = String.format("INSERT INTO %s (name, recordLabel) values (?,?) ", super.table);
        try (Connection conn = this.getDbConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, item.getName());
            if(item.getRecordLabel() != null){
                statement.setString(2, item.getRecordLabel());
            } else {
                statement.setNull(2, Types.CHAR);
            }
            int numberOfUpdatedRows = statement.executeUpdate();
            if (numberOfUpdatedRows > 0) {
                System.out.println("Database updated");
                return;
            } else {
                throw new SQLException("Update failed");
            }
        }
    }

    @Override
    public void update(Act item) throws SQLException {
        if(item.getId() == null){
            throw new IllegalArgumentException("id must be provided");
        }
        String sql = String.format("UPDATE %s SET name = ?, recordLabel = ? WHERE id = ? ", super.table);
        try (Connection conn = this.getDbConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, item.getName());
            if(item.getRecordLabel() != null){
                statement.setString(2, item.getRecordLabel());
            } else {
                statement.setNull(2, Types.CHAR);
            }
            statement.setInt(3, item.getId());

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
