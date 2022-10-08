package com.example.homy1.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    @Test
    @DisplayName("Filled User Fields")
    public void toString_PrintsString_IfAllFieldsAreFilled()  {
        String userString;

        User user = new User("Connor", "Pan", "cpan", "yes", "User");
        user.setId(0);

        userString = user.toString();
        String expectedMessage = "ID = 0FirstName=Connor, lastName=Pan, username=cpan, Permission Level=User]";

        assertEquals(expectedMessage, userString);
    }

    @Test
    @DisplayName("No Username User Fields")
    public void toString_PrintsString_IfNoUsername()  {
        String userString;

        User user = new User("Connor", "Pan", null, "yes", "User");
        user.setId(0);

        userString = user.toString();
        String expectedMessage = "ID = 0FirstName=Connor, lastName=Pan, username=null, Permission Level=User]";

        assertEquals(expectedMessage, userString);
    }

    @Test
    @DisplayName("No Password User Fields")
    public void toString_PrintsString_IfNoPassword()  {
        String userString;

        User user = new User("Connor", "Pan", "cpan", null, "User");
        user.setId(0);

        userString = user.toString();
        String expectedMessage = "ID = 0FirstName=Connor, lastName=Pan, username=cpan, Permission Level=User]";

        assertEquals(expectedMessage, userString);
    }

    @Test
    @DisplayName("No Filled Fields")
    public void toString_PrintsString_IfNoFilledFields()  {
        String userString;

        User user = new User();
        user.setId(0);

        userString = user.toString();
        String expectedMessage = "ID = 0FirstName=null, lastName=null, username=null, Permission Level=null]";

        assertEquals(expectedMessage, userString);
    }
}
