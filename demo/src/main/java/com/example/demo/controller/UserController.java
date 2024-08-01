package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.Data;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/reserve")
    public Reservation reserveRoom(@RequestBody ReservationRequest request) {
        User user = new User(); // Fetch user from database
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

@Data
class ReservationRequest {
    private Long userId;
    private Long roomId;
    private Long slotId;
    private MeetingType type;
    private int numberOfPeople;
}
