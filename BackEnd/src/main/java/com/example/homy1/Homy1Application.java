package com.example.homy1;

import com.example.homy1.dao.UserDao;
import com.example.homy1.dao.UserDaoImpl;
import com.example.homy1.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@SpringBootApplication
public class Homy1Application {

    public static void main(String[] args) {
        SpringApplication.run(Homy1Application.class, args);


    }

}
