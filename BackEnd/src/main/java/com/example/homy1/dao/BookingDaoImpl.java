package com.example.homy1.dao;

import com.example.homy1.model.Booking;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class BookingDaoImpl {
    private final String TABLE_NAME = "booking";

    public void setup() throws SQLException {
        try (Connection connection = Database.getConnection();
             Statement stmt = connection.createStatement();) {
            String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(id INTEGER NOT NULL AUTO_INCREMENT, eventName VARCHAR(255) NOT NULL," +
                    "eventStart VARCHAR(255) NOT NULL," + "eventEnd VARCHAR(255) NOT NULL,"
                    + "eventDetails VARCHAR(255) NOT NULL,"+ "userID INTEGER NOT NULL," + "PRIMARY KEY (id))";

            stmt.executeUpdate(sql);
        }
    }


    public Booking createBooking(Integer eventID, String eventName, String eventStart, String eventEnd, String eventDetails, Integer userID, Integer doctorID) throws SQLException {
        String sql = "INSERT INTO " + TABLE_NAME + " VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = Database.getConnection();

             PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setInt(1, eventID);
            stmt.setString(2, eventName);
            stmt.setString(3, eventStart);
            stmt.setString(4, eventEnd);
            stmt.setString(5, eventDetails);
            stmt.setInt(6, userID);
            stmt.setInt(7, doctorID);

            stmt.executeUpdate();
            return new Booking(eventID, eventName, eventStart, eventEnd, eventDetails, userID, doctorID);

        }
    }

    public Integer getCount() throws SQLException{
        String sql = "SELECT COUNT(*) FROM booking";
        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);) {
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                return resultSet.getInt(1);
            }
        }
        return 0;

    }
}
