package com.example.homy1.model;

import com.example.homy1.dao.UserDao;
import com.example.homy1.dao.UserDaoImpl;

import java.sql.SQLException;

public class Model {
    private UserDao userDao;
    private User currentUser;

    public Model() {
        userDao = new UserDaoImpl();
    }

    public void setup() throws SQLException {
        userDao.setup();
    }
    public UserDao getUserDao() {
        return userDao;
    }




}
