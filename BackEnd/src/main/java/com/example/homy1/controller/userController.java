package com.example.homy1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.homy1.dao.UserDao;
import java.util.List;

public class userController {
    @RestController
    @RequestMapping("/user")
    public class UserController {

        @Autowired
        private UserDao userDao;



    }
}