package com.example.database;

import lombok.Data;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.Properties;

@Data
public class DataSource {

    private static volatile DataSource instance;

    private final String url;
    private final String user;
    private final String password;
    private final String driver;

    @SneakyThrows
    private DataSource() {
        Properties dbProperties = new Properties();
        dbProperties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("database.properties"));
        this.url = dbProperties.getProperty("url");
        this.user = dbProperties.getProperty("user");
        this.password = dbProperties.getProperty("password");
        this.driver = dbProperties.getProperty("driver");
    }

    public static DataSource getInstance() {
        if (instance == null) {
            synchronized (DataSource.class) {
                if (instance == null) {
                    instance = new DataSource();
                }
            }
        }
        return instance;
    }

    @SneakyThrows
    public Connection getConnection() throws SQLException {
        Class.forName(driver);
        return DriverManager.getConnection(url, user, password);
    }
}
