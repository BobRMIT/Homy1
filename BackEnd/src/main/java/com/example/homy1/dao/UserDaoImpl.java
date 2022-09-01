package com.example.homy1.dao;

import com.example.homy1.model.User;

import java.sql.*;

public class UserDaoImpl implements UserDao {
    private final String TABLE_NAME = "users";

    public UserDaoImpl() {
    }

    @Override
    public void setup() throws SQLException {
        try (Connection connection = Database.getConnection();
             Statement stmt = connection.createStatement();) {
            String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (ID int NOT NULL AUTO_INCREMENT,"
                    + "username VARCHAR(15) NOT NULL," + "password VARCHAR(10) NOT NULL," + "firstName VARCHAR(15) NOT NULL,"
                    + "lastName VARCHAR(15) NOT NULL," + "permission VARCHAR(8) NOT NULL," + "PRIMARY KEY (username))";
            stmt.executeUpdate(sql);
        }
    }
    @Override
    public User createUser(String firstName, String lastName, String username, String password, String permission) throws SQLException {
        String sql = "INSERT INTO " + TABLE_NAME + " (username, firstName)WHERE username = ? AND password = ? AND firstName = ? AND lastName = ? AND permission = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, firstName);
            stmt.setString(4, lastName);
            stmt.setString(5, permission);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setFirstName(rs.getString("firstName"));
                    user.setLastName(rs.getString("lastName"));
                    user.setLastName(rs.getString("permission"));
                    return user;
                }
                return null;
            }
        }
    }
    @Override
<<<<<<< Updated upstream
    public User getUser(String username, String password) throws SQLException {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE username = ? AND password = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setString(1, username);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setFirstName(rs.getString("firstName"));
                    user.setLastName(rs.getString("lastName"));
                    return user;
                }
                return null;
            }
        }
    }
    @Override
=======
    public User updateUser(String firstName, String lastName, String username, String password, String permission) throws SQLException{
        return null;

    }
    @Override
    public boolean removeUser(String username) throws SQLException{
        return false;
    }

    @Override
>>>>>>> Stashed changes
    public User listUsers(){
        return null;
    }
}
