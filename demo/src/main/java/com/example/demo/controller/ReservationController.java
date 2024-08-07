package com.example.demo.controller;

import com.example.demo.dto.ReservationRequest;
import com.example.demo.exception.SlotNotFoundException;
import com.example.demo.exception.UserNotFoundException;
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
    public ResponseEntity<Reservation> reserveRoom(@RequestBody ReservationRequest request) {
        User user = userService.getUserDetails(request.getUserId());
        Room room = roomService.getDetails(request.getRoomId());
        Slot slot = slotService.getDetails(request.getSlotId());
        Reservation reservation = reservationService.reserveRoom(user, room, slot, request.getType(), request.getNumberOfPeople());
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

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(SlotNotFoundException.class)
    public ResponseEntity<String> handleSlotNotFoundException(SlotNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
