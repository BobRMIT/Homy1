package com.example.homy1;


//import com.example.homy1.model.TestUser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

import com.example.homy1.dao.UserDao;
import com.example.homy1.dao.UserDaoImpl;
import com.example.homy1.model.User;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;


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
        @RequestMapping("/create/")
        public String addUser() throws SQLException {

        try{
            userDao.createUser(1,"Toe","Biden","toeBiden123","abc213", "Admin" );
        }
        catch(SQLIntegrityConstraintViolationException e){
            return "User already exists";

            }
            return "Test 2";

        }

        @RequestMapping("/search/{username}/{password}")
        public String searchUser(@PathVariable String username,@PathVariable String password) throws SQLException {

            User n = new User();
            //n = userDao.getUser("toeBiden123", "abc213");
            n = userDao.getUser(username, password);
            if(n == null){
                System.out.println("No User found");
                return "";
            }
            else{
                System.out.println(n);
                String result = n.toString();
                System.out.println(result);

                return result;
            }
        }
    }
}
