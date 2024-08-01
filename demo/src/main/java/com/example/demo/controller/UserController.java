package com.example.demo.controller;

import com.example.demo.model.Reservation;
import com.example.demo.model.Room;
import com.example.demo.model.Slot;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.dto.UserRequest;
import com.example.demo.dto.ReservationRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser(@RequestBody UserRequest userRequest) {
        User user = new User();
        user.setName(userRequest.getName());
        // Set other fields as needed
        return userService.saveUser(user);
    }

    @PutMapping("/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody UserRequest userRequest) {
        User userDetails = new User();
        userDetails.setName(userRequest.getName());
        // Set other fields as needed
        return userService.updateUser(userId, userDetails);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable Long userId) {
        return userService.getUserDetails(userId);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/reserve")
    public Reservation reserveRoom(@RequestBody ReservationRequest request) {
        User user = userService.getUserDetails(request.getUserId());
        Room room = new Room(); // Fetch room from database
        Slot slot = new Slot(); // Fetch slot from database
        return userService.reserveRoom(user, room, slot, request.getType(), request.getNumberOfPeople());
    }

    @DeleteMapping("/cancel/{reservationId}")
    public void cancelReservation(@PathVariable Long reservationId) {
        Reservation reservation = new Reservation(); // Fetch reservation from database
        userService.cancelReservation(reservation);
    }
}
