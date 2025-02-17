package com.daineit.javase11.databasesjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import com.daineit.javase11.databasesjdbc.models.User;
import com.daineit.javase11.databasesjdbc.repositories.UserRepository;

public class DatabasesWithJDBCApp {
    static String DB_NAME = "jdbctestdb";
    static String SERVER_HOST = "<server-host>/";
    static String URL = "jdbc:postgresql://" + DatabasesWithJDBCApp.SERVER_HOST  + DB_NAME;
    static String USER_NAME = "pgAdmin";
    static String PSW = "<password>";
    public static void main(String[] args) throws SQLException {
        Properties props = new Properties();
        props.put("user", USER_NAME);
        props.put("password", PSW);
        props.put("ssl", true);
        props.put("sslmode", "require");
        try(Connection conn = DriverManager.getConnection(URL, props)){
            System.out.println(conn);
            UserRepository repo = new UserRepository(conn);
            List<User> users = repo.getAll();
            for(User user: users){
                System.out.printf("%d - %s %s (%s)%n", user.getId(), user.getFirstName(), user.getLastName(), user.getGender());
            }
        }
    }
}
