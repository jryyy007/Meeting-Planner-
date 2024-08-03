package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserServiceIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setName("Test User");
    }

    @Test
    @Transactional
    public void testSaveUser() {
        User savedUser = userRepository.save(user);
        assertNotNull(savedUser.getId());
    }

    @Test
    @Transactional
    public void testFindUserById() {
        User savedUser = userRepository.save(user);
        User foundUser = userService.getUserDetails(savedUser.getId());
        assertNotNull(foundUser);
        assertEquals("Test User", foundUser.getName());
    }
}