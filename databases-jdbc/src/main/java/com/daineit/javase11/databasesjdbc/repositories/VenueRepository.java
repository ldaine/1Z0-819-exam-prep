package com.daineit.javase11.databasesjdbc.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.daineit.javase11.databasesjdbc.models.Venue;

public class VenueRepository extends Repository<Venue> {

    public VenueRepository(Connection conn) {
        super(conn, "venues");
    }

    public List<Venue> getAll() throws SQLException {
        List<Venue> result = new ArrayList<>();
        String sql = "SELECT id, name, capacity FROM " + super.table;
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
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
        String sql = String.format("INSERT INTO %s (name, capacity) values (%s,%d) ", super.table, item.getName(), item.getCapacity());
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
