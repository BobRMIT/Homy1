package com.example.homy1.dao;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class UserDaoImplTest {
    UserDaoImpl userDao = new UserDaoImpl();

    @BeforeAll
    public void init() throws SQLException {

        userDao.setup();

    }

    @Test
    @DisplayName("Duplicate username")
    public void givenDuplicateUsername_whenUsernameExists_thenThrowSQLException() throws SQLException {
        userDao.createUser(1, "Bob", "Qian", "bobby123", "password", "User");
        userDao.createUser(1, "Connor", "B", "bobby123", "password1", "User");

    }
}
