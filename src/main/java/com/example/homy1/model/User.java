package com.example.homy1.model;

public class User {
    private Integer ID;
    private String firstName;
    private String lastName;
    private String username;
    private String password;


    public User() {
    }

    public User(Integer ID, String username, String password){
        this.ID = ID;
        this.username = username;
        this.password = password;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }






}
