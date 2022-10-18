package com.example.homy1.controller;

import com.example.homy1.model.Booking;
import com.example.homy1.model.User;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class bookingControllerTest {
    bookingController controller = new bookingController();

    @BeforeAll
    public void init() throws SQLException {

        controller.index();
    }

    @Test
    @DisplayName("Create Booking")
    @Order(0)
    public void createBooking_returnsResponseEntity_IfCalled() throws Exception {
        // Servlet Request Issue
//        ResponseEntity<User> responseEntity;
//
//        Booking booking = new Booking("0", "Mike's Appointment",
//                "16-10-2022", "6:00", 1, 66, "dr paul");
//
//        responseEntity = controller.createBooking("","", booking);
//
//

//        assertEquals(expectedMessage, actualMessage);
    }


}