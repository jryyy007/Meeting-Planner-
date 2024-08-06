package com.example.demo.controller;

import com.example.demo.dto.ReservationRequest;
import com.example.demo.dto.UserRequest;
import com.example.demo.model.Reservation;
import com.example.demo.model.Room;
import com.example.demo.model.Slot;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.service.RoomService;
import com.example.demo.service.SlotService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final RoomService roomService;
    private final SlotService slotService;

    public UserController(UserService userService, RoomService roomService, SlotService slotService) {
        this.userService = userService;
        this.roomService = roomService;
        this.slotService = slotService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserRequest userRequest) {
        User user = new User();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        User savedUser = userService.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody UserRequest userRequest) {
        User userDetails = new User();
        userDetails.setName(userRequest.getName());
        userDetails.setEmail(userRequest.getEmail());
        userDetails.setPassword(userRequest.getPassword());
        User updatedUser = userService.updateUser(userId, userDetails);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable Long userId) {
        User user = userService.getUserDetails(userId);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/reserve")
    public ResponseEntity<Reservation> reserveRoom(@RequestBody ReservationRequest request) {
        User user = userService.getUserDetails(request.getUserId());
        Room room = roomService.getDetails(request.getRoomId());
        Slot slot = slotService.getDetails(request.getSlotId());
        Reservation reservation = userService.reserveRoom(user, room, slot, request.getType(), request.getNumberOfPeople());
        return new ResponseEntity<>(reservation, HttpStatus.CREATED);
    }

    @DeleteMapping("/cancel/{reservationId}")
    public ResponseEntity<Void> cancelReservation(@PathVariable Long reservationId) {
        userService.cancelReservation(reservationId);
        return ResponseEntity.noContent().build();
    }
}
