package com.example.homy1.model;

import com.example.homy1.dao.UserDao;
import com.example.homy1.dao.UserDaoImpl;

import javax.persistence.*;
import java.sql.SQLException;

public class User {
    public User(){

    }
    public User(String firstName, String lastName, String username, String password, String permission){
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.permission = permission;
    }

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @GeneratedValue(strategy= GenerationType.AUTO)
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String permission;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        return "ID = " + id + "FirstName=" + firstName + ", lastName=" + lastName + ", username=" + username + ", Permission Level="+ permission + "]";
    }

}
