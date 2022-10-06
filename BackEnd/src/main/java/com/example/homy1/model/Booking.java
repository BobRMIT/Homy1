package com.example.homy1.model;

import javax.persistence.Column;
import javax.persistence.Id;

public class Booking {
    @Id
    @Column(name = "eventID", nullable = true)
    private Integer eventID;
    private String eventName;
    private String eventStart;
    private String eventEnd;
    private String eventDetails;
    private Integer userID;
    private Integer doctorID;


    public Booking(){

    }

    public Booking(String eventName, String eventStart, String eventEnd, String eventDetails, Integer userID, Integer doctorID) {
        this.eventName = eventName;
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
        this.eventDetails = eventDetails;
        this.userID = userID;
        this.doctorID = doctorID;
    }

    public Integer getEventID() {
        return eventID;
    }

    public void setEventID(Integer eventID) {
        this.eventID = eventID;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventStart() {
        return eventStart;
    }

    public void setEventStart(String eventStart) {
        this.eventStart = eventStart;
    }

    public String getEventEnd() {
        return eventEnd;
    }

    public void setEventEnd(String eventEnd) {
        this.eventEnd = eventEnd;
    }

    public String getEventDetails() {
        return eventDetails;
    }

    public void setEventDetails(String eventDetails) {
        this.eventDetails = eventDetails;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(Integer doctorID) {
        this.doctorID = doctorID;
    }
}
