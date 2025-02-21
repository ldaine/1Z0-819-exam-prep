package com.daineit.javase11.databasesjdbc.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.daineit.javase11.databasesjdbc.models.Venue;

public class VenueRepository extends Repository<Venue> {

    public VenueRepository() {
        super("venues");
    }

    @Override
    public List<Venue> getAll() throws SQLException {
        List<Venue> result = new ArrayList<>();
        String sql = "SELECT id, name, capacity FROM " + super.table;
        try (Connection conn = super.getDbConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                Integer capacity = rs.getInt("capacity");
                result.add(new Venue(id, name, capacity));
            }
        }

        return result;
    }

    @Override
    public void add(Venue item) throws SQLException {
        String sql = String.format("INSERT INTO %s (name, capacity) values (?,?) ", super.table);
        try (Connection conn = this.getDbConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, item.getName());
            statement.setInt(2, item.getCapacity());
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
    public void update(Venue item) throws SQLException {
        if(item.getId() == null){
            throw new IllegalArgumentException("id must be provided");
        }
        String sql = String.format("UPDATE %s SET name = ?, capacity = ? WHERE id = ?", super.table, item.getId());
        try (Connection conn = this.getDbConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, item.getName());
            statement.setInt(2, item.getCapacity());
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
