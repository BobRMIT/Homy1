package com.example.homy1.dao;

import com.example.homy1.model.User;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class UserDaoImpl{

    private final String TABLE_NAME = "users";
    private final String TABLE_NAME2 = "bookings";

    public UserDaoImpl() {

    }

    /**
     * setting up users table
     * @throws SQLException
     */
    public void setup() throws SQLException {
        try (Connection connection = Database.getConnection();
             Statement stmt = connection.createStatement();) {
            String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(id INTEGER NOT NULL AUTO_INCREMENT, username VARCHAR(255) NOT NULL UNIQUE," +
                    "password VARCHAR(255) NOT NULL," + "firstName VARCHAR(255) NOT NULL,"
                    + "lastName VARCHAR(255) NOT NULL," + "permission VARCHAR(255) NOT NULL," + "PRIMARY KEY (id,username))";
            stmt.executeUpdate(sql);

        }
    }

    /**
     * Create user and input it into database
     * @param id id of new user, not needed
     * @param firstName first name of new user
     * @param lastName last name of new user
     * @param username username of new user
     * @param password password of new user
     * @param permission permission of new user
     * @return returns newly created User object
     * @throws SQLException
     */
    public User createUser(Integer id, String firstName, String lastName, String username, String password, String permission) throws SQLException {
        String sql = "INSERT INTO " + TABLE_NAME + " (username, password, firstName, lastName, permission) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = Database.getConnection();

             PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, firstName);
            stmt.setString(4, lastName);
            stmt.setString(5, permission);

            stmt.executeUpdate();
            return new User(firstName, lastName, username, password, permission);

        }
    }

    /**
     * Searcher for user in database using username and password
     * @param username username of desired user
     * @param password password of desired user
     * @return User object of user after if found in the database, if not found, null is returned
     * @throws SQLException
     */
    public User getUser(String username, String password) throws SQLException {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE username = ? AND password = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);){
            stmt.setString(1, username);
            stmt.setString( 2, password);
            stmt.executeQuery();
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next())  {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setFirstName(rs.getString("firstName"));
                    user.setLastName(rs.getString("lastName"));
                    user.setPermission(rs.getString("permission"));
                    //System.out.println(user.toString());
                    return user;
                }
                return null;
            }
        }
    }

    /**
     * Updating user, unused
     * @param firstName
     * @param lastName
     * @param username
     * @param password
     * @param permission
     * @return returns null if user is not found, returns new user object with updates
     * @throws SQLException
     */
    public User updateUser(String firstName, String lastName, String username, String password, String permission) throws SQLException{
        String sql = "UPDATE " + TABLE_NAME + " SET firstName = ?, lastName = ? WHERE username = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, username);
            stmt.executeUpdate();

            User user = getUser(username, password);
             if(user == null) {
                 return null;
             }
             user.setUsername(username);
             user.setPassword(password);
             user.setFirstName(firstName);
             user.setLastName(lastName);
             user.setPermission(permission);
             return user;

        }
    }

    /**
     * Removes user in tables given username and password
     * @param username username of desired user
     * @param password password of desired user
     * @return true if sql command runs
     * @throws SQLException
     */
    public boolean removeUser(String username, String password) throws SQLException{
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE username = ? AND password = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.execute();
        }
        return true;
    }

    /**
     * Get count of user database entries, for ID
     * @return Returns integer count, 0 if none found
     * @throws SQLException
     */
    public Integer getCount() throws SQLException{
        String sql = "SELECT COUNT(*) FROM users";
        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);) {
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                return resultSet.getInt(1);
            }
        }
        return 0;
    }

    public User listUsers(){
        return null;
    }

    /**
     * Finds id username already exists in user table
     * @param username username string to check for in database
     * @return true if username exists, false if it does not exist
     * @throws SQLException
     */
    public boolean CheckUsername(String username) throws SQLException{
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE username = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);){
            stmt.setString(1, username);
            stmt.executeQuery();
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next())  {
                    return true;
                }
                return false;
            }
        }
    }
}
