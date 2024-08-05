package com.example.demo.service;

import com.example.demo.model.Room;
import com.example.demo.model.Slot;
import com.example.demo.repository.RoomRepository;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.model.Reservation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class RoomService {

    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;

    public RoomService(RoomRepository roomRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.reservationRepository = reservationRepository;
    }

    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    public Room updateRoom(Long roomId, Room roomDetails) {
        return roomRepository.findById(roomId)
                .map(room -> {
                    room.setName(roomDetails.getName());
                    room.setCapacity(roomDetails.getCapacity());
                    room.setEquipment(roomDetails.getEquipment());
                    return roomRepository.save(room);
                })
                .orElseThrow(() -> new IllegalArgumentException("Room not found with id: " + roomId));
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
        return roomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("Room not found with id: " + roomId));
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }
}
