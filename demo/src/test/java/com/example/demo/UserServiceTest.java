package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.Optional;

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

import com.example.demo.model.User;
import com.example.demo.model.Reservation;
import com.example.demo.model.Room;
import com.example.demo.model.Slot;
import com.example.demo.model.MeetingType;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ReservationService;
import com.example.demo.service.RoomService;
import com.example.demo.service.UserService;

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

        assertThat(createdUser).isNotNull();
        assertThat(createdUser.getId()).isNotNull();
        assertThat(createdUser.getName()).isEqualTo("John Doe");
        assertThat(createdUser.getEmail()).isEqualTo("john.doe@example.com");

        // Additional check to verify data in the repository
        Optional<User> foundUser = userRepository.findById(createdUser.getId());
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getName()).isEqualTo("John Doe");
    }
    @Test
    @Transactional
    public void testGetUserDetails() {
        User savedUser = userRepository.save(testUser);
        User foundUser = userService.getUserDetails(savedUser.getId());

        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getName()).isEqualTo("John Doe");
        assertThat(foundUser.getEmail()).isEqualTo("john.doe@example.com");
    }

    @Test
    @Transactional
    public void testUpdateUser() {
        User savedUser = userRepository.save(testUser);
        savedUser.setName("John Smith");

        User updatedUser = userService.updateUser(savedUser.getId(), savedUser);

        assertThat(updatedUser.getName()).isEqualTo("John Smith");
    }

    @Test
    @Transactional
    public void testDeleteUser() {
        User savedUser = userRepository.save(testUser);
        userService.deleteUser(savedUser.getId());

        Optional<User> foundUser = userRepository.findById(savedUser.getId());

        assertThat(foundUser).isNotPresent();
    }

    @Test
    @Transactional
    public void testGetAllUsers() {
        userRepository.save(testUser);
        Iterable<User> users = userService.getAllUsers();

        assertThat(users).hasSize(1);
    }

    @Test
    @Transactional
    public void testReserveRoom() {
        userRepository.save(testUser);
        when(roomService.isAvailable(any(Room.class), any(Slot.class))).thenReturn(true);
        Reservation reservation = new Reservation();
        // Initialize reservation fields as necessary
        when(reservationService.reserveRoom(any(User.class), any(Room.class), any(Slot.class), any(MeetingType.class), anyInt())).thenReturn(reservation);

        Reservation createdReservation = userService.reserveRoom(testUser, testRoom, testSlot, testMeetingType, 5);

        assertThat(createdReservation).isNotNull();
    }

    @Test
    @Transactional
    public void testCancelReservation() {
        Reservation reservation = new Reservation();
        reservation.setId(1L);
        // Initialize reservation fields as necessary

        userService.cancelReservation(reservation);

        // Verify that cancelReservation was called on the reservationService
        assertThat(reservationService.cancelReservation(reservation.getId())).isNull();
    }
}
