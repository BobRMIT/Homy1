package com.example.homy1;

import com.example.homy1.model.TestUser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

@SpringBootApplication
public class Homy1Application {

    public static void main(String[] args) {
        SpringApplication.run(Homy1Application.class, args);
    }

    @RestController
    public class BlogController {

        @RequestMapping("/")
        public String index() {
            System.out.println("tested");
            return "Congratulations from BlogController.java";

        }

        @RequestMapping("/TestUser/{id}")
        public String GetData(@PathVariable String id) throws JsonProcessingException {
            ObjectMapper mapper = new ObjectMapper();
            TestUser firstUser = new TestUser(1,"Tonald Drump");
            TestUser secondUser = new TestUser(2,"Boe Jiden");
            System.out.println("Requesting User: " + id);

            //Temp database entries

            if (id.equals("1")){
                try {
                    String json = mapper.writeValueAsString(firstUser);
                    return json;
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                    return "Error";
                }
            }else if (id.equals("2")){
                try {
                    String json = mapper.writeValueAsString(secondUser);
                    return json;
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                    return "Error";
                }

            }else{
                return "Invalid Login";
            }
        }
    }
}
