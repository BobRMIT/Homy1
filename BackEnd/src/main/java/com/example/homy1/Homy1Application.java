package com.example.homy1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class Homy1Application {

    public static void main(String[] args) {
        SpringApplication.run(Homy1Application.class, args);


    }

}
