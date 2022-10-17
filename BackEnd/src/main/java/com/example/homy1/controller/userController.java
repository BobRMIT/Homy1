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

    /**
     * sets us user database
     * @return returns setup complete string if successful
     * @throws SQLException
     */
    @RequestMapping("/")
    public String index() throws SQLException {
        userDao = new UserDaoImpl();
        userDao.setup();
        //System.out.println("tested");
        return "Setup Complete";

    }

    /**
     * endpoint to search for user in database given username and password
     * @param username username of desired user
     * @param password password of desired user
     * @return user object of given credentials
     * @throws SQLException
     */
    @GetMapping(value = "/{username}/{password}/", produces = "application/json")
    public User get(@PathVariable String username, @PathVariable String password) throws SQLException {
        user = userDao.getUser(username, password);

        return user;
    }

    /**
     * Search for existing username in database
     * @param username the username to search for
     * @return boolean true if username exists, false if it does not exist
     * @throws SQLException
     */
    @RequestMapping("check/{username}/")
    public Boolean checkUserExists(@PathVariable String username) throws SQLException {
        System.out.println("looking for username: " + username);
        return userDao.CheckUsername(username);

    }


    /**
     * Creating user in database
     * @return ResponseEntity
     * @throws Exception
     */
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

        return ResponseEntity.created(location).build();
    }

    /**
     *
     * @return
     * @throws SQLException
     */
    @RequestMapping("/test")
    public String searchUser() throws SQLException {
        User n = new User();
        userDao.getUser("toeBiden123", "abc123");

        return "It works!";
    }

    /**
     * TBA
     * @param username
     * @param password
     * @return
     * @throws SQLException
     */
    @PostMapping("/search")
    @ResponseBody
    public String searchUser(@RequestParam String username, @RequestParam String password) throws SQLException {

        User n = new User();
        n = userDao.getUser(username, password);

        if(n == null) {
            //System.out.println("No user found");
        }
        else{
            //System.out.println(n.toString());
        }
        return "Searching User";
    }

}