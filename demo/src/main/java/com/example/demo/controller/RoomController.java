package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomService roomService;
    private final ReservationService reservationService;
    private final SlotService slotService;

    public RoomController(RoomService roomService, ReservationService reservationService, SlotService slotService) {
        this.roomService = roomService;
        this.reservationService = reservationService;
        this.slotService = slotService;
    }

    @PostMapping
    public ResponseEntity<Room> createRoom(@RequestBody Room room) {
        Room savedRoom = roomService.saveRoom(room);
        return new ResponseEntity<>(savedRoom, HttpStatus.CREATED);
    }

    @PutMapping("/{roomId}")
    public ResponseEntity<Room> updateRoom(@PathVariable Long roomId, @RequestBody Room roomDetails) {
        Room updatedRoom = roomService.updateRoom(roomId, roomDetails);
        return ResponseEntity.ok(updatedRoom);
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long roomId) {
        roomService.deleteRoom(roomId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<Room> getRoom(@PathVariable Long roomId) {
        Room room = roomService.getDetails(roomId);
        return ResponseEntity.ok(room);
    }

    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms() {
        List<Room> rooms = roomService.getAllRooms();
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/available")
    public ResponseEntity<Boolean> isRoomAvailable(@RequestParam Long roomId, @RequestParam Long slotId) {
        Room room = roomService.getDetails(roomId);
        Slot slot = slotService.getDetails(slotId);
        boolean isAvailable = room != null && slot != null && roomService.isAvailable(room, slot);
        return ResponseEntity.ok(isAvailable);
    }
}
