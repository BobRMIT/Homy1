package com.example.homy1.controller;


import com.example.homy1.dao.BookingDaoImpl;
import com.example.homy1.dao.UserDaoImpl;
import com.example.homy1.model.Booking;
import com.example.homy1.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.sql.SQLException;

@RestController
@RequestMapping("/booking")
public class bookingController {
    @Autowired
    private BookingDaoImpl bookingDao;
    private Booking booking;

    @RequestMapping("/")
    public String index() throws SQLException {
        bookingDao = new BookingDaoImpl();
        bookingDao.setup();
        System.out.println("tested");
        return "Setup Complete";

    }

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

        bookingDao.createBooking(booking.getEventID(), booking.getEventName(), booking.getStartDate(), booking.getEndDate(), booking.getDescription(), booking.getUserID()
        , booking.getDoctorID());

        //Create resource location
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(booking.getEventID())
                .toUri();

        //Send location in response
        System.out.println(ResponseEntity.created(location).body(0));
        System.out.println(ResponseEntity.created(location).build());
        return ResponseEntity.created(location).build();
    }
}
