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
            String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "username VARCHAR(15) NOT NULL," +
                    "password VARCHAR(10) NOT NULL," + "firstName VARCHAR(15) NOT NULL,"
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
            return new User(username, password, firstName, lastName, permission);

        }
    }
    @Override
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
                    user.setPermission(rs.getString("permission"));
                    return user;
                }
                return null;
            }
        }
    }
    @Override
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
    @Override
    public boolean removeUser(String username) throws SQLException{
        return false;
    }

    @Override
    public User listUsers(){
        return null;
    }
}
