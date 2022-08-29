package com.example.homy1.model;

import com.fasterxml.jackson.core.JsonProcessingException;

public class TestUser {
    private int id;
    private String Name;

    public TestUser(int id, String title) {
        this.setId(id);
        this.setName(title);

    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return Name;
    }
    public void setName(String Name) {
        this.Name = Name;
    }

    @Override
    public String toString() {
        return "1";
    }
}
