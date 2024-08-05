package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;
    private final RoomService roomService;
    private final UserService userService;
    private final SlotService slotService;

    public ReservationController(ReservationService reservationService, RoomService roomService, UserService userService, SlotService slotService) {
        this.reservationService = reservationService;
        this.roomService = roomService;
        this.userService = userService;
        this.slotService = slotService;
    }

    @PostMapping
    public ResponseEntity<Reservation> reserveRoom(@RequestParam Long userId, @RequestParam Long roomId, @RequestParam Long slotId,
                                                   @RequestParam MeetingType type, @RequestParam int numberOfPeople) {
        User user = userService.getUserDetails(userId);
        Room room = roomService.getDetails(roomId);
        Slot slot = slotService.getDetails(slotId);
        Reservation reservation = reservationService.reserveRoom(user, room, slot, type, numberOfPeople);
        return new ResponseEntity<>(reservation, HttpStatus.CREATED);
    }

    @DeleteMapping("/{reservationId}")
    public ResponseEntity<Void> cancelReservation(@PathVariable Long reservationId) {
        reservationService.cancelReservation(reservationId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{reservationId}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable Long reservationId, @RequestBody Reservation reservationDetails) {
        Reservation updatedReservation = reservationService.updateReservation(reservationId, reservationDetails);
        return ResponseEntity.ok(updatedReservation);
    }

    @GetMapping("/{reservationId}")
    public ResponseEntity<Reservation> getReservation(@PathVariable Long reservationId) {
        Reservation reservation = reservationService.getReservationDetails(reservationId);
        return ResponseEntity.ok(reservation);
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> reservations = reservationService.getAllReservations();
        return ResponseEntity.ok(reservations);
    }
}
