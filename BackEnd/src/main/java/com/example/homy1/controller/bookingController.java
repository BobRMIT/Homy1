package com.example.homy1.controller;


import com.example.homy1.dao.BookingDaoImpl;
import com.example.homy1.dao.UserDaoImpl;
import com.example.homy1.model.Booking;
import com.example.homy1.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.awt.*;
import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@RequestMapping("/booking")
public class bookingController {
    @Autowired
    private BookingDaoImpl bookingDao;
    private Booking booking;

    /**
     * Booking database setup
     * @return String setup Complete
     * @throws SQLException
     */
    @RequestMapping("/")
    public String index() throws SQLException {
        bookingDao = new BookingDaoImpl();
        bookingDao.setup();
        return "Setup Complete";

    }

    /**
     * Creating booking endpoint, input json of Booking object
    */
    @PostMapping(value = "/create", consumes = "application/json", produces =  "application/json")
    public ResponseEntity<User> createBooking(
            @RequestHeader(name = "X-COM-PERSIST", required = false) String headerPersist,
            @RequestHeader(name = "X-COM-LOCATION", required = false, defaultValue = "ASIA") String headerLocation,
            @RequestBody Booking booking)
            throws Exception
    {


        //Generate resource id
        Integer id = bookingDao.getCount() + 1;
        booking.setEventID(id);

        //add resource

        bookingDao.createBooking(booking.getEventID(), booking.getEventName(), booking.getEventStart(), booking.getEventEnd(), booking.getEventDetails(), booking.getUserID()
        , booking.getDoctorName());

        //Create resource location
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(booking.getEventID())
                .toUri();

        //Send location in response
        //System.out.println(ResponseEntity.created(location).body(0));
        //System.out.println(ResponseEntity.created(location).build());
        return ResponseEntity.created(location).build();
    }

    //This function may be obsolete
    /*
    @GetMapping(value ="/{userID}/{bookingD}", produces = "application/json")
    public String getBooking(@PathVariable Integer userID, @PathVariable Integer bookingID) throws SQLException{
       booking = bookingDao.getBookingList(bookingID);

       return booking.toString();
    }*/

    /**
     * Updating booking from json
     */
    @PostMapping(value = "/update", consumes = "application/json", produces =  "application/json")
    public ResponseEntity<User> updateBooking(
        @RequestHeader(name = "X-COM-PERSIST", required = false) String headerPersist,
        @RequestHeader(name = "X-COM-LOCATION", required = false, defaultValue = "ASIA") String headerLocation,
        @RequestBody Booking booking)
            throws Exception
        {
            bookingDao.updateBooking(booking.getEventID(), booking.getEventName(), booking.getEventStart(), booking.getEventEnd(), booking.getEventDetails(), booking.getUserID()
                    , booking.getDoctorID());

            //Create resource location
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(booking.getEventID())
                    .toUri();

            //Send location in response
            ///System.out.println(ResponseEntity.created(location).body(0));
            //System.out.println(ResponseEntity.created(location).build());
            return ResponseEntity.created(location).build();
        }

    /**
     * Get list of all doctors in database
     * @return ArrayList of all Doctors firstname and lastname
     * @throws SQLException
     */
    @GetMapping(value ="/getDoctors/", produces = "application/json")
    public ArrayList<String> getDoctors() throws SQLException{
        //System.out.print(bookingDao.getDoctorNames());

        return bookingDao.getDoctorNames();
    }

    /**
     * Get all bookings with specific doctor
     * @param doctorsID integer of doctors ID
     * @return returns ArrayList of strings containing information of bookings with given doctors ID
     * @throws SQLException
     */
    @GetMapping(value ="/GetBookingList/{doctorsID}", produces = "application/json")
    public ArrayList<String> getBooking(@PathVariable Integer doctorsID) throws SQLException{
        //System.out.print(bookingDao.getBookingList(doctorsID));

        return bookingDao.getBookingList(doctorsID);
    }

}
