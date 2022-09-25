package com.example.homy1.model;

import javax.persistence.Column;
import javax.persistence.Id;

public class Event {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    private String eventName;
    private String startDate;
    private String endDate;
    private String description;


    public Event(){

    }

    public Event(Integer id, String eventName, String startDate, String endDate, String description) {
        this.id = id;
        this.eventName = eventName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
