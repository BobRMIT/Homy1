package com.example.homy1.dao;

import com.example.homy1.model.User;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class UserDaoImpl{
    private final String TABLE_NAME = "users";

    public UserDaoImpl() {

    }

    public void setup() throws SQLException {
        try (Connection connection = Database.getConnection();
             Statement stmt = connection.createStatement();) {
            String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(id INTEGER NOT NULL AUTO_INCREMENT, username VARCHAR(255) NOT NULL," +
                    "password VARCHAR(255) NOT NULL," + "firstName VARCHAR(255) NOT NULL,"
                    + "lastName VARCHAR(255) NOT NULL," + "permission VARCHAR(255) NOT NULL," + "PRIMARY KEY (username))";
            stmt.executeUpdate(sql);
        }
    }

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
                    System.out.println(user.toString());
                    return user;
                }
                return null;
            }
        }
    }

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

    public boolean removeUser(String username, String password) throws SQLException{
        String sql = "DELETE " + TABLE_NAME + " WHERE username = ? AND password = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.execute();
        }
        return true;
    }

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
}
