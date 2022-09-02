package com.example.homy1.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Database {
    static String username = "root";
    static String password = "password";

    private static final String DB_URL = "jdbc:mysql://localhost:3306/homy1";

    public static Connection getConnection() throws SQLException {
        Properties connectionProps = new Properties();
        connectionProps.put("user", username);
        connectionProps.put("password", password);
        // DriverManager is the basic service for managing a set of JDBC drivers
        // Can also pass username and password
        return DriverManager.getConnection(DB_URL, connectionProps);
    }
}

