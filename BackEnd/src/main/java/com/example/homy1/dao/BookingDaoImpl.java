package com.example.homy1.dao;

import com.example.homy1.model.Booking;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository
public class BookingDaoImpl {
    private final String TABLE_NAME = "booking";
    private final String TABLE_NAME2 = "users";

    public void setup() throws SQLException {
        try (Connection connection = Database.getConnection();
             Statement stmt = connection.createStatement();) {
            String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(eventID INTEGER NOT NULL AUTO_INCREMENT, eventName VARCHAR(255) NOT NULL," +
                    "eventStart VARCHAR(255) NOT NULL," + "eventEnd VARCHAR(255) NOT NULL,"
                    + "eventDetails VARCHAR(255) NOT NULL,"+ "userID INTEGER NOT NULL," + "doctorID INTEGER NOT NULL," + "PRIMARY KEY (eventID))";

            stmt.executeUpdate(sql);
        }
    }

//  createBooking() function
//  Creates an entry in the MYSQL database and returns the new booking added
    public Booking createBooking(Integer eventID, String eventName, String eventStart, String eventEnd, String eventDetails, Integer userID, Integer doctorID) throws SQLException {
        String sql = "INSERT INTO " + TABLE_NAME + "(eventName, eventStart, eventEnd, eventDetails, userID, doctorID) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = Database.getConnection();

             PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setString(1, eventName);
            stmt.setString(2, eventStart);
            stmt.setString(3, eventEnd);
            stmt.setString(4, eventDetails);
            stmt.setInt(5, userID);
            stmt.setInt(6, doctorID);

            stmt.executeUpdate();
            return new Booking(eventName, eventStart, eventEnd, eventDetails, userID, doctorID);

        }
    }

    public boolean removeBooking(Integer eventID, Integer userID) throws SQLException{
        String sql = "DELETE * FROM " + TABLE_NAME + " WHERE eventID = ? AND userID = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setInt(1, eventID);
            stmt.setInt(2, userID);
            return stmt.execute();
        }
    }

    public Booking getBooking(Integer eventID) throws SQLException{
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE eventID = ?";
        try (Connection connection = Database.getConnection();

             PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setInt(1, eventID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next())  {
                    Booking booking = new Booking();
                    booking.setEventID(rs.getInt("eventID"));
                    booking.setEventName(rs.getString("eventName"));
                    booking.setEventStart(rs.getString("eventStart"));
                    booking.setEventEnd(rs.getString("eventEnd"));
                    booking.setEventDetails(rs.getString("eventDetails"));
                    booking.setUserID(rs.getInt("userID"));
                    booking.setDoctorID(rs.getInt("doctorID"));
//                    System.out.println(user.toString());
                    return booking;
                }
                return null;
            }
        }
    }

    public Booking updateBooking(Integer eventID, String eventName, String eventStart, String eventEnd, String eventDetails, Integer userID, Integer doctorID) throws SQLException{
        String sql = "UPDATE " + TABLE_NAME + " SET eventName = ?, eventStart = ?, eventEnd = ?, eventDetails = ?, userID = ?, doctorID = ?";
        try (Connection connection = Database.getConnection();

             PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setString(1, eventName);
            stmt.setString(2, eventStart);
            stmt.setString(3, eventEnd);
            stmt.setString(4, eventDetails);
            stmt.setInt(5, userID);
            stmt.setInt(6, doctorID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next())  {
                    Booking booking = new Booking();
                    booking.setEventID(rs.getInt("eventID"));
                    booking.setEventName(rs.getString("eventName"));
                    booking.setEventStart(rs.getString("eventStart"));
                    booking.setEventEnd(rs.getString("eventEnd"));
                    booking.setEventDetails(rs.getString("eventDetails"));
                    booking.setUserID(rs.getInt("userID"));
                    booking.setDoctorID(rs.getInt("doctorID"));
//                    System.out.println(user.toString());
                    return booking;
                }
                return null;
            }
        }
    }

    public Integer getCount() throws SQLException{
        String sql = "SELECT COUNT(*) FROM" + TABLE_NAME;
        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);) {
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                return resultSet.getInt(1);
            }
        }
        return 0;

    }

    public ArrayList<String> getDoctorNamesAndIDs() throws SQLException{
        String sql = "SELECT username FROM " + TABLE_NAME2 + " WHERE permission = ?";
        ArrayList<String> DocNameList = new ArrayList<String>();

        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "Doctor");
            System.out.println(stmt);
            ResultSet rs = stmt.executeQuery();
            //Doc
            while (rs.next()){
                DocNameList.add(rs.getString("username"));
                System.out.println(rs.getString("username"));
            }

            return DocNameList;

        }


    }

}
