package com.example.homy1.model;

import javax.persistence.Column;
import javax.persistence.Id;

public class Booking {
    @Id
    @Column(name = "eventID", nullable = false)
    private Integer eventID;
    private String eventName;
    private String startDate;
    private String endDate;
    private String description;
    private Integer userID;
    private Integer doctorID;


    public Booking(){

    }

    public Booking(Integer eventID, String eventName, String startDate, String endDate, String description, Integer userID, Integer doctorID) {
        this.eventID = eventID;
        this.eventName = eventName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.userID = userID;
        this.doctorID = doctorID;
    }

    public Integer getEventID() {
        return eventID;
    }

    public void setEventID(Integer eventID) {
        this.eventID = eventID;
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

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
