package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.service.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@AllArgsConstructor
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private ReservationService reservationService;
    @Autowired
    private SlotService slotService;

    @PostMapping
    public Room createRoom(@RequestBody Room room) {
        return roomService.saveRoom(room);
    }

    @PutMapping("/{roomId}")
    public Room updateRoom(@PathVariable Long roomId, @RequestBody Room roomDetails) {
        return roomService.updateRoom(roomId, roomDetails);
    }

    @DeleteMapping("/{roomId}")
    public void deleteRoom(@PathVariable Long roomId) {
        roomService.deleteRoom(roomId);
    }

    @GetMapping("/{roomId}")
    public Room getRoom(@PathVariable Long roomId) {
        return roomService.getDetails(roomId);
    }

    @GetMapping
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping("/available")
    public boolean isRoomAvailable(@RequestParam Long roomId, @RequestParam Long slotId) {
        Room room = roomService.getDetails(roomId);
        Slot slot = slotService.getDetails(slotId);
        return room != null && slot != null && roomService.isAvailable(room, slot);
    }
}
