package com.example.demo.repository;

import com.example.demo.model.Availability;
import com.example.demo.model.Room;
import com.example.demo.model.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Long> {
    // Find availabilities by room
    List<Availability> findByRoom(Room room);

    // Find availabilities by slot
    List<Availability> findBySlot(Slot slot);

    // Find availabilities by room and slot
    List<Availability> findByRoomAndSlot(Room room, Slot slot);
}
