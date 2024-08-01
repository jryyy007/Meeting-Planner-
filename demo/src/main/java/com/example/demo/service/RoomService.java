package com.example.demo.service;

import com.example.demo.model.Room;
import com.example.demo.model.Slot;
import com.example.demo.repository.RoomRepository;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    // Save a new room
    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    // Update an existing room
    public Room updateRoom(Long roomId, Room updatedRoom) {
        return roomRepository.findById(roomId)
                .map(room -> {
                    room.setName(updatedRoom.getName());
                    room.setCapacity(updatedRoom.getCapacity());
                    // Update other fields as needed
                    return roomRepository.save(room);
                })
                .orElse(null);
    }

    // Delete a room by ID
    public void deleteRoom(Long roomId) {
        roomRepository.deleteById(roomId);
    }
    public boolean isAvailable(Room room, Slot slot) {
        LocalDateTime slotStart = slot.getStartTime();
        LocalDateTime slotEnd = slot.getEndTime();
        LocalDateTime bufferStart = slotStart.minusHours(1);
        LocalDateTime bufferEnd = slotEnd.plusHours(1);

        // Check if there's an existing reservation that conflicts with the cleaning buffer
        return reservationRepository.findAllByRoomAndSlotOverlaps(room, bufferStart, bufferEnd).isEmpty();
    }

    public Room getDetails(Long roomId) {
        Optional<Room> room = roomRepository.findById(roomId);
        return room.orElse(null);
    }
}
