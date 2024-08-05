package com.example.demo;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MeetingPlannerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeetingPlannerApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner demo(UserService userService) {
//        return (args) -> {
//            User testUser = new User();
//            testUser.setName("testUser");
//            testUser.setEmail("test.user@mail.com");
//            testUser.setPassword("testPassword");
//
//            User createdUser = userService.saveUser(testUser);
//            System.out.println("Created user: " + createdUser);
//        };
//    }
}