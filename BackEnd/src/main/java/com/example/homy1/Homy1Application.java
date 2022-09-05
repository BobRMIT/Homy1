package com.example.homy1;

import com.example.homy1.dao.UserDao;
import com.example.homy1.dao.UserDaoImpl;
import com.example.homy1.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

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
    }
    @RestController
    public class BlogController {

        @RequestMapping("/")
        public String index() throws SQLException {
            setup();
            System.out.println("tested");
            return "Test 1";

        }
        @RequestMapping("/create")
        public String addUser() throws SQLException {

            try {
                userDao.createUser(1, "Toe", "Biden", "toeBiden123", "abc213", "Admin");
            }catch(SQLException e){
                System.out.println("User already exitst");
            }
            return "Test 2";

        }
        @RequestMapping("/test")
        public String searchUser() throws SQLException {
            User n = new User();
            userDao.getUser("toeBiden123", "abc123");

            return "It works!";
        }
        @PostMapping("/search")
        @ResponseBody
        public String searchUser(@RequestParam String username, @RequestParam String password) throws SQLException {

            User n = new User();
            n = userDao.getUser(username, password);

            if(n == null) {
                System.out.println("No user found");
            }
            else{
                System.out.println(n.toString());
            }

            return "Searching User";

        }
    }
}
