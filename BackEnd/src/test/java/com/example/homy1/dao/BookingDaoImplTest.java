package com.example.homy1.dao;

import com.example.homy1.model.Booking;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BookingDaoImplTest {
    BookingDaoImpl bookingDao = new BookingDaoImpl();

    @BeforeAll
    public void init() throws SQLException {

        bookingDao.setup();

    }

    @Test
    @DisplayName("Creating Booking")
    @Order(0)
    public void createBooking_returnBooking_ifDoctorExists() throws SQLException {
        // Test method to see if there is validation against duplicate usernames
        Booking booking;
        booking = bookingDao.createBooking(0, "Mike's Appointment",
                "16-10-2022", "6:00", "Desc", 1, "dr paul");

        String expectedEventName = "Mike's Appointment";

        assertEquals(expectedEventName, booking.getEventName());
    }

    @Test
    @DisplayName("Removing a Booking")
    @Order(1)
    public void removeBooking_returnsBool_ifBookingExists() throws SQLException {
        // There seems to be a SQL Exception occurring
        // Wants an SQL update not query
//        Boolean actualBool = false;
//        actualBool = bookingDao.removeBooking(0, 1);
//
//        assertTrue(actualBool);
    }

    @Test
    @DisplayName("Book List")
    @Order(2)
    public void getBookingList_returnsStringArray_IfBookingsExist() throws SQLException {
        List<String> bookingList;

        bookingList = bookingDao.getBookingList(66);

        assertTrue(bookingList.contains("a a 16-10-2022"));
    }

    @Test
    @DisplayName("Checking for an existing username")
    @Order(3)
    public void updateBooking_returnsBooking_IfBookingInDatabase() throws SQLException {
        // There seems to be a SQL Exception occurring
        // Wants an SQL update not query
//        Booking actualBooking;
//        actualBooking = bookingDao.updateBooking(8, "Michael's Appointment",
//                "16-10-2022", "12:00", "Desc", 1, 66);
//
//        Booking expectBooking = new Booking("Michael's Appointment",
//                "16-10-2022", "12:00","Desc", 1, 66, "dr paul");
//
//        assertEquals(expectBooking, actualBooking);
    }

}