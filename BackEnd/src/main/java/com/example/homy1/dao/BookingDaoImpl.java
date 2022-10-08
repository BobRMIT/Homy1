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
    public Booking createBooking(Integer eventID, String eventName, String eventStart, String eventEnd, String eventDetails, Integer userID, String doctorName) throws SQLException {

        String sql = "INSERT INTO " + TABLE_NAME + "(eventName, eventStart, eventEnd, eventDetails, userID, doctorID) VALUES (?, ?, ?, ?, ?, ?)";

        int doctorID = getDoctorIDFromName(doctorName);

        try (Connection connection = Database.getConnection();

             PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setString(1, eventName);
            stmt.setString(2, eventStart);
            stmt.setString(3, eventEnd);
            stmt.setString(4, eventDetails);
            stmt.setInt(5, userID);
            stmt.setInt(6, doctorID);

            stmt.executeUpdate();
            return new Booking(eventName, eventStart, eventEnd, eventDetails, userID, doctorID, doctorName);

        }
    }

    private Integer getDoctorIDFromName(String doctorname) throws SQLException {

        String sql = "SELECT * FROM " + TABLE_NAME2 + " WHERE permission = ?";
        String DocNameDatabase;

        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "Doctor");
            //System.out.println(stmt);
            ResultSet rs = stmt.executeQuery();
            //Doc
            while (rs.next()){
                DocNameDatabase = rs.getString("firstName") + " " + rs.getString("lastName");
                if (doctorname.equals(DocNameDatabase)){
                    return rs.getInt("id");
                }
            }

            return 0;

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

    public ArrayList<String> getBookingList(Integer doctorID) throws SQLException{
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE doctorID = ?";
        ArrayList<String> BookingList = new ArrayList<String>();

        //System.out.println(sql);
        try (Connection connection = Database.getConnection();

             PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setInt(1, doctorID);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next())  {
                    getFullNameFromID(rs.getString("userID"));

                    BookingList.add( getFullNameFromID(rs.getString("userID")) + " " + rs.getString("eventStart"));


                }
                return BookingList;
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

    public ArrayList<String> getDoctorNames() throws SQLException{
        String sql = "SELECT * FROM " + TABLE_NAME2 + " WHERE permission = ?";
        ArrayList<String> DocNameList = new ArrayList<>();

        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "Doctor");
            //System.out.println(stmt);
            ResultSet rs = stmt.executeQuery();
            //Doc
            while (rs.next()){
                DocNameList.add(rs.getString("firstName") + " " + rs.getString("lastName"));
                //System.out.println(rs.getString("firstName") + " " + rs.getString("lastName"));
            }

            return DocNameList;

        }



        }

    private String getFullNameFromID(String ID) throws SQLException{
        String sql = "SELECT * FROM " + TABLE_NAME2 + " WHERE id = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, ID);
            //System.out.println(stmt);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return rs.getString("firstName") + " " + rs.getString("lastName");

        }

    }

}


