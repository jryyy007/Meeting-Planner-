package com.example.demo.controller;

import com.example.demo.model.Room;
import com.example.demo.model.Slot;
import com.example.demo.service.RoomService;
import com.example.demo.service.SlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private SlotService slotService;

    /**
     * Checks if a room is available for a given slot.
     *
     * @param roomId ID of the room
     * @param slotId ID of the slot
     * @return true if the room is available, false otherwise
     */
    @GetMapping("/availability")
    public boolean checkAvailability(@RequestParam Long roomId, @RequestParam Long slotId) {
        Room room = roomService.getDetails(roomId);
        Slot slot = slotService.getDetails(slotId); // Fetch the slot details by ID
        return room != null && slot != null && roomService.isAvailable(room, slot);
    }

    /**
     * Retrieves details of a specific room.
     *
     * @param roomId ID of the room
     * @return Room details
     */
    @GetMapping("/{roomId}")
    public Room getRoomDetails(@PathVariable Long roomId) {
        return roomService.getDetails(roomId);
    }

    /**
     * Adds a new room.
     *
     * @param room Room object to be added
     * @return Added Room object
     */
    @PostMapping("/")
    public Room addRoom(@RequestBody Room room) {
        return roomService.saveRoom(room);
    }

    /**
     * Updates an existing room.
     *
     * @param roomId ID of the room to be updated
     * @param room Updated Room object
     * @return Updated Room object
     */
    @PutMapping("/{roomId}")
    public Room updateRoom(@PathVariable Long roomId, @RequestBody Room room) {
        return roomService.updateRoom(roomId, room);
    }

    /**
     * Deletes a room.
     *
     * @param roomId ID of the room to be deleted
     */
    @DeleteMapping("/{roomId}")
    public void deleteRoom(@PathVariable Long roomId) {
        roomService.deleteRoom(roomId);
    }
}
