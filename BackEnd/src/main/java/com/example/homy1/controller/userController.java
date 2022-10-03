package com.example.homy1.controller;

import com.example.homy1.dao.UserDaoImpl;
import com.example.homy1.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

@RestController
@RequestMapping("/users")
public class userController {
    @Autowired
    private UserDaoImpl userDao;
    private User user;

    @RequestMapping("/")
    public String index() throws SQLException {
        userDao = new UserDaoImpl();
        userDao.setup();
        System.out.println("tested");
        return "Setup Complete";

    }

    @GetMapping(value = "/{username}/{password}/", produces = "application/json")
    public User get(@PathVariable String username, @PathVariable String password) throws SQLException {
        user = userDao.getUser(username, password);

        return user;
    }


    @RequestMapping("check/{username}/")
    public Boolean checkUserExists(@PathVariable String username) throws SQLException {
        System.out.println("looking for username: " + username);
        return userDao.CheckUsername(username);

    }



    @PostMapping(value = "/create", consumes = "application/json", produces =  "application/json")
    public ResponseEntity<User> addUser(
            @RequestHeader(name = "X-COM-PERSIST", required = false) String headerPersist,
            @RequestHeader(name = "X-COM-LOCATION", required = false, defaultValue = "ASIA") String headerLocation,
            @RequestBody User user)
            throws Exception
    {


        //Generate resource id
        Integer id = userDao.getCount() + 1;
        user.setId(id);

        //add resource

        userDao.createUser(user.getId(), user.getFirstName(), user.getLastName(), user.getUsername(), user.getPassword(), user.getPermission());

        //Create resource location
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        //Send location in response
        System.out.println(ResponseEntity.created(location).body(0));
        System.out.println(ResponseEntity.created(location).build());
        return ResponseEntity.created(location).build();
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