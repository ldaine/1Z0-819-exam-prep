package com.daineit.javase11.databasesjdbc.repositories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.daineit.javase11.databasesjdbc.models.User;

public class UserRepository {

    private final String table = "users";
    private final Connection connection;

    public UserRepository(Connection conn) {
        this.connection = conn;
    }

    public List<User> getAll() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT id, first_name, last_name, gender FROM " + table;
        var statement = this.connection.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            Integer id = rs.getInt(1);
            String firstName = rs.getString(2);
            String lastName = rs.getString(3);
            String gender = rs.getString(4);
            users.add(new User(id, firstName, lastName, gender));
        }

        return users;
    }
}
