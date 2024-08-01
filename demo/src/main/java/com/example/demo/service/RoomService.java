package com.example.demo.service;

import com.example.demo.model.Room;
import com.example.demo.model.Slot;
import com.example.demo.repository.RoomRepository;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.model.Reservation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    public Room updateRoom(Long roomId, Room roomDetails) {
        Room room = roomRepository.findById(roomId).orElse(null);
        if (room != null) {
            room.setName(roomDetails.getName());
            room.setCapacity(roomDetails.getCapacity());
            room.setEquipment(roomDetails.getEquipment());
            return roomRepository.save(room);
        } else {
            return null;
        }
    }

    public void deleteRoom(Long roomId) {
        roomRepository.deleteById(roomId);
    }

    public boolean isAvailable(Room room, Slot slot) {
        LocalDateTime slotStart = slot.getStartTime();
        LocalDateTime slotEnd = slot.getEndTime();
        LocalDateTime bufferStart = slotStart.minusHours(1);
        LocalDateTime bufferEnd = slotEnd.plusHours(1);

        List<Reservation> conflictingReservations = reservationRepository.findAllByRoomAndSlotOverlaps(room.getId(), bufferStart, bufferEnd);

        return conflictingReservations.isEmpty();
    }

    public Room getDetails(Long roomId) {
        Optional<Room> room = roomRepository.findById(roomId);
        return room.orElse(null);
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }
}
