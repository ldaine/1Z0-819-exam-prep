package com.daineit.javase11.databasesjdbc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class DBConfiguration {
    private final String FILE_PATH = "database.conf";
    private static DBConfiguration INSTANCE;

    final String host;
    final String user;
    final String password;
    final String dbName;

    private DBConfiguration() throws IOException {
        Properties dbConfigProperties = new Properties();
        try (InputStream input = DBConfiguration.class.getClassLoader().getResourceAsStream(FILE_PATH)) {
            dbConfigProperties.load(input);
            this.host = dbConfigProperties.getProperty("db.host");
            this.user = dbConfigProperties.getProperty("db.user");
            this.password = dbConfigProperties.getProperty("db.password");
            this.dbName = dbConfigProperties.getProperty("db.name");
        }
    }

    public static DBConfiguration getInstance() {
        if (INSTANCE == null) {
            try {
                INSTANCE = new DBConfiguration();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return INSTANCE;
    }

    public String getUrl() {
        return "jdbc:postgresql://" + this.host + "/" + this.dbName;
    }

    public Properties getProperties() {
        Properties props = new Properties();
        props.put("user", this.user);
        props.put("password", this.password);
        props.put("ssl", true);
        props.put("sslmode", "require");
        return props;
    }
}