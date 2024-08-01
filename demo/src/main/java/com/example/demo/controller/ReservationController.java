package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.service.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@AllArgsConstructor
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private UserService userService;

    @Autowired
    private SlotService slotService;

    @PostMapping
    public Reservation reserveRoom(@RequestParam Long userId, @RequestParam Long roomId, @RequestParam Long slotId,
                                   @RequestParam MeetingType type, @RequestParam int numberOfPeople) {
        User user = userService.getUserDetails(userId);
        Room room = roomService.getDetails(roomId);
        Slot slot = slotService.getDetails(slotId);
        return reservationService.reserveRoom(user, room, slot, type, numberOfPeople);
    }

    @DeleteMapping("/{reservationId}")
    public void cancelReservation(@PathVariable Long reservationId) {
        reservationService.cancelReservation(reservationId);
    }

    @PutMapping("/{reservationId}")
    public Reservation updateReservation(@PathVariable Long reservationId, @RequestBody Reservation reservationDetails) {
        return reservationService.updateReservation(reservationId, reservationDetails);
    }

    @GetMapping("/{reservationId}")
    public Reservation getReservation(@PathVariable Long reservationId) {
        return reservationService.getReservationDetails(reservationId);
    }

    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }
}
