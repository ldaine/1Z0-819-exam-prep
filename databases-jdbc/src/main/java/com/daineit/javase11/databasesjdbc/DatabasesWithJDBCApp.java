package com.daineit.javase11.databasesjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import com.daineit.javase11.databasesjdbc.models.Act;
import com.daineit.javase11.databasesjdbc.repositories.ActRepository;

public class DatabasesWithJDBCApp {
    static final DBConfiguration dbConfig = new DBConfiguration();
    public static void main(String[] args) throws SQLException {

        final String url = "jdbc:postgresql://" + dbConfig.getUrl()  + "/" + dbConfig.getName();
        Properties props = new Properties();
        props.put("user", dbConfig.getUser());
        props.put("password", dbConfig.getPassword());
        props.put("ssl", true);
        props.put("sslmode", "require");
        try(Connection conn = DriverManager.getConnection(url, props)){
            System.out.println(conn);
            ActRepository repo = new ActRepository(conn);
            List<Act> result = repo.getAll();
            for(Act act: result){
                System.out.printf("%d - %s - %s%n", act.getId(), act.getName(), act.getRecordLabel());
            }
        }
    }
}
