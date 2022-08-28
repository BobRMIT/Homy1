package com.example.homy1.model;

public class User {
    private Integer ID;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String permission;


    public User() {
    }

    public User(Integer ID, String firstName, String lastName, String username, String password, String permission){
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.permission = permission;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        return "Employee [id=" + ID + ", firstName=" + firstName + ", lastName=" + lastName + ", username=" + username + ", Permission Level="+ permission + "]";
    }

}
