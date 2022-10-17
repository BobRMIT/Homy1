package com.example.homy1.dao;

import com.example.homy1.model.Booking;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository
public class BookingDaoImpl {
    private final String TABLE_NAME = "booking";
    private final String TABLE_NAME2 = "users";

    /**
     * Booking table setup
     **/
    public void setup() throws SQLException { //table setup
        try (Connection connection = Database.getConnection();
             Statement stmt = connection.createStatement();) {
            String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(eventID INTEGER NOT NULL AUTO_INCREMENT, eventName VARCHAR(255) NOT NULL," +
                    "eventStart VARCHAR(255) NOT NULL," + "eventEnd VARCHAR(255) NOT NULL,"
                    + "eventDetails VARCHAR(255) NOT NULL,"+ "userID INTEGER NOT NULL," + "doctorID INTEGER NOT NULL," + "PRIMARY KEY (eventID))";

            stmt.executeUpdate(sql);
        }
    }

    /**
     * Creates an entry in the MYSQL database and returns the new booking added
     * @param eventID id of booking, obsolete for now
     * @param eventName type of event
     * @param eventStart date event starts
     * @param eventEnd time event ends
     * @param eventDetails notes about booking
     * @param userID ID of user making booking
     * @param doctorName name of docker being booked
     * @return Booking object of new booking
     * @throws SQLException
     */
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

    /**
     * Getting ID of doctor from database
     * @param doctorname string of doctors name (full name)
     * @return Integer of doctors ID, returns 0 id not found
     **/
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

    /**
     * Removes booking from database
     * @param eventID ID of booking on table
     * @param userID ID of user removing booking
     * @return true if successful, false otherwise
     **/
    public boolean removeBooking(Integer eventID, Integer userID) throws SQLException{
        String sql = "DELETE * FROM " + TABLE_NAME + " WHERE eventID = ? AND userID = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setInt(1, eventID);
            stmt.setInt(2, userID);
            return stmt.execute();
        }
    }

    /**
     * Find all bookings given a doctors ID
     * @param doctorID ID of booking on table
     * @return ArrayList of strings containing all bookings with doctors ID
     **/
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


    /**
     * Update bookings in database
     * @param eventID ID of booking on table, not required
     * @param eventName
     * @param eventStart
     * @param eventEnd
     * @param eventDetails
     * @param userID
     * @param doctorID
     * @return Booking object of new information
     **/
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


