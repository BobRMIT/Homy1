package com.example.homy1.dao;

import com.example.homy1.model.User;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import java.sql.SQLException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserDaoImplTest {
    UserDaoImpl userDao = new UserDaoImpl();

    @BeforeAll
    public void init() throws SQLException {

        //userDao.createUser(null, "Pan", "C", "panc", "a", "User");
        userDao.setup();

    }

    @Test
    @DisplayName("Creating duplicate username")
    @Order(0)
    public void givenDuplicateUsername_whenUsernameExists_thenThrowSQLException() throws SQLException {
        // Test method to see if there is validation against duplicate usernames
        try {
            // Try to put in the username 'bobby123' to make sure there will be a duplicate but avoid throwing an exception
            userDao.createUser(1, "Bob", "Qian", "bobby123", "password", "User");
        }
        catch(Exception e) {}

        Exception exception = assertThrows(SQLException.class,
        () -> userDao.createUser(1, "Connor", "B", "bobby123", "password1", "User"),
        "SQL Exception");

        String expectedMessage = "Duplicate entry 'bobby123' for key 'users.username'";

        assertEquals(expectedMessage, exception.getLocalizedMessage());
    }

    @Test
    @DisplayName("Creating a user")
    @Order(1)
    public void createUser_returnsString_IfUserNotInDatabase() throws SQLException {
        String actualMessage = "";
        try {
            // Try to delete and create in the username 'panc'
            User user = userDao.createUser(null, "Pan", "C", "panc", "a", "User");
            actualMessage = user.toString();
        }
        catch(Exception e) {}

        String expectedMessage = "ID = nullFirstName=Pan, lastName=C, username=panc, Permission Level=User]";

        assertEquals(expectedMessage, actualMessage);
    }

//    @Test
//    @DisplayName("Updating a user")
//    @Order(2)
//    public void updateUser_returnsString_IfUserInDatabase() throws SQLException {
//        String actualMessage = "";
//        try {
//            // Try to update the user with the username 'panc'
//            User user = userDao.updateUser("Pan", "Si", "panc", "a", "User");
//            actualMessage = user.toString();
//        }
//        catch(Exception e) {}
//
//        String expectedMessage = "ID = nullFirstName=Pan, lastName=Si, username=panc, Permission Level=User]";
//
//        assertEquals(expectedMessage, actualMessage);
//    }

    @Test
    @DisplayName("Deleting a user")
    @Order(3)
    public void removeUser_returnsTrue_IfUserInDatabase() throws SQLException {
        boolean removeBool = false;
        try {
            // Try to delete user with the username 'panc'
            removeBool = userDao.removeUser("panc", "a");

        }
        catch(Exception e) {}

        assertTrue(removeBool);
    }

    @Test
    @DisplayName("Checking for an existing username")
    @Order(4)
    public void CheckUsername_returnsTrue_IfUsernameInDatabase() throws SQLException {
        boolean checkBool = false;
        try {
            // Try to check if user with the username 'bobby123' is in database
            checkBool = userDao.CheckUsername("bobby123");

        }
        catch(Exception e) {}

        assertTrue(checkBool);
    }

    @Test
    @DisplayName("Checking for a username not in database")
    @Order(5)
    public void CheckUsername_returnsFalse_IfUsernameNotInDatabase() throws SQLException {
        boolean checkBool = true;
        try {
            // Try to check if user with the username 'bobby123' is in database
            checkBool = userDao.CheckUsername("bobby124");

        }
        catch(Exception e) {}

        assertFalse(checkBool);
    }

}
