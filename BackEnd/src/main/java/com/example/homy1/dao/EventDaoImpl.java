package com.example.homy1.dao;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class EventDaoImpl {
    private final String TABLE_NAME = "event";

    public void setup() throws SQLException {
        try (Connection connection = Database.getConnection();
             Statement stmt = connection.createStatement();) {
            String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(id INTEGER NOT NULL AUTO_INCREMENT, eventName VARCHAR(255) NOT NULL," +
                    "eventStart VARCHAR(255) NOT NULL," + "eventEnd VARCHAR(255) NOT NULL,"
                    + "eventDetails VARCHAR(255) NOT NULL,"+ "userID INTEGER NOT NULL," + "PRIMARY KEY (id))";

            stmt.executeUpdate(sql);
        }
    }


}
