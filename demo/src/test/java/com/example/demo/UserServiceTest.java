package com.example.demo;

import com.example.demo.model.MeetingType;
import com.example.demo.model.Room;
import com.example.demo.model.Slot;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ReservationService;
import com.example.demo.service.RoomService;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Mock
    private ReservationService reservationService;

    @Mock
    private RoomService roomService;

    @InjectMocks
    @Autowired
    private UserService userService;

    private User testUser;
    private Room testRoom;
    private Slot testSlot;
    private MeetingType testMeetingType;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Create a test user
        testUser = new User();
        testUser.setName("John Doe");
        testUser.setEmail("john.doe@example.com");
        testUser.setPassword("password");

        // Create a test room
        testRoom = new Room();
        testRoom.setName("Conference Room");

        // Create a test slot
        testSlot = new Slot();
        // Initialize slot fields as necessary

        // Create a test meeting type
        testMeetingType = MeetingType.VC; // Example meeting type
    }

    @AfterEach
    public void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    @Transactional
    public void testSaveUser() {
        User testUser = new User();
        testUser.setName("John Doe");
        testUser.setEmail("john.doe@example.com");
        testUser.setPassword("password");

        User createdUser = userService.saveUser(testUser);

        System.out.println("Created user: " + createdUser);

        Optional<User> foundUser = userRepository.findById(createdUser.getId());

        System.out.println("Found user: " + foundUser);

        assertThat(foundUser).isPresent();
        User user = foundUser.get();
        assertEquals(testUser.getName(), user.getName());
        assertEquals(testUser.getEmail(), user.getEmail());
        assertEquals(testUser.getPassword(), user.getPassword());
    }

    @Test
    @Transactional
    public void testGetUserDetails() {
        User savedUser = userRepository.save(testUser);
        User foundUser = userService.getUserDetails(savedUser.getId());

        System.out.println("Found user: " + foundUser);

        assertThat(foundUser).isNotNull();
        assertEquals(testUser.getName(), foundUser.getName());
        assertEquals(testUser.getEmail(), foundUser.getEmail());
    }

    @Test
    @Transactional
    public void testUpdateUser() {
        User savedUser = userRepository.save(testUser);
    }}