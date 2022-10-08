package com.example.homy1.controller;

import org.junit.jupiter.api.*;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class userControllerTest {
    userController controller = new userController();

    @BeforeAll
    public void init() throws SQLException {

        controller.index();
    }

    @Test
    @DisplayName("Calling index")
    @Order(0)
    public void index_returnsString_IfCalled() {
        // Test method to see if UserDao setups properly

        String actualMessage = "";

        try {
            // Try to call index function
            actualMessage = controller.index();
        }
        catch(Exception e) {}

        String expectedMessage = "Setup Complete";

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Check If User in Database")
    @Order(1)
    public void checkUserExists_returnsBoolean_IfUsernameInDatabase() {

        Boolean actualBool = false;

        try {
            // Try to check if user is in database
            actualBool = controller.checkUserExists("bobby123");
        }
        catch(Exception e) {}

        Boolean expectedBool = true;

        assertEquals(expectedBool, actualBool);
    }

    @Test
    @DisplayName("Check for User Not in Database")
    @Order(2)
    public void checkUserExists_returnsBool_IfUsernameNotInDatabase() {
        Boolean actualBool = true;

        try {
            // Try to check if user is in database
            actualBool = controller.checkUserExists("bobby124");
        }
        catch(Exception e) {}

        Boolean expectedBool = false;

        assertEquals(expectedBool, actualBool);
    }

    @Test
    @DisplayName("Search for User in Database")
    @Order(3)
    public void searchUser_returnsString_IfUserInDatabase() {
        String actualString = "";

        try {
            // Try to search for user in database
            actualString = controller.searchUser("bobby123", "password");
        }
        catch(Exception e) {}

        String expectedString = "Searching User";

        assertEquals(expectedString, actualString);
    }

    @Test
    @DisplayName("Search for User that is not in Database")
    @Order(4)
    public void searchUser_returnsString_IfUserNotInDatabase() {
        String actualString = "";

        try {
            // Try to search for user in database
            actualString = controller.searchUser("bobby124", "password");
        }
        catch(Exception e) {}

        String expectedString = "Searching User";

        assertEquals(expectedString, actualString);
    }

}
