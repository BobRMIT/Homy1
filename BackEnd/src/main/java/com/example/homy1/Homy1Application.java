package com.example.homy1;


//import com.example.homy1.model.TestUser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

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
            System.out.println("testing active");
            return "Active";

        }
        @PostMapping("/create/")
        public String createItem(
                @RequestBody User user) throws SQLException {

        try{
            userDao.createUser(2, //change in database needed here, Database ID increment
                    user.getFirstName(),
                    user.getLastName(),
                    user.getUsername(),
                    user.getPassword(),
                    user.getPermission());

            //userDao.createUser(1,"Toe","Biden","toeBiden123","abc213", "Admin" );
        }
        catch(SQLIntegrityConstraintViolationException e){
            System.out.println("User already exists");
            return "User already exists";
            }

        return "Success";
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
