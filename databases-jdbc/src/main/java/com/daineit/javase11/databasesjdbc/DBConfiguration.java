package com.daineit.javase11.databasesjdbc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBConfiguration {
    private final String propertiesFilePath = "database.conf";
    private Properties properties;

    public DBConfiguration() {
        properties = new Properties();
        try (InputStream input = DBConfiguration.class.getClassLoader().getResourceAsStream(propertiesFilePath)) {
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public String getUrl() {
        return properties.getProperty("db.host");
    }

    public String getUser() {
        return properties.getProperty("db.user");
    }

    public String getPassword() {
        return properties.getProperty("db.password");
    }

    public String getName() {
        return properties.getProperty("db.name");
    }
}