package com.example.homy1.dao;

import com.example.homy1.model.User;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
public interface UserDao {
    void setup() throws SQLException;
    User createUser(String firstName, String lastName, String username, String password, String permission) throws SQLException;
    User getUser(String username, String password) throws SQLException;
    User updateUser(String firstName, String lastName, String username, String password, String permission) throws SQLException;
    boolean removeUser(String username) throws SQLException;
    User listUsers();
}
