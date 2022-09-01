package com.example.homy1;

import com.example.homy1.dao.UserDao;
import com.example.homy1.dao.UserDaoImpl;
import com.example.homy1.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@SpringBootApplication
public class Homy1Application {
    private UserDao userDao;
    private User user;


    public static void main(String[] args) {
        SpringApplication.run(Homy1Application.class, args);


    }
    public void setup() throws SQLException {
        userDao = new UserDaoImpl();
        userDao.setup();
        userDao.createUser("Toe", "Biden", "toebiden123", "abc123", "Admin");
    }
    @RestController
    public class BlogController {

        @RequestMapping("/")
        public String index() throws SQLException {
            setup();
            System.out.println("tested");
            System.out.println(userDao.getUser("toebiden123", "abc123").toString());
            return "Hello";

        }
    }
}
