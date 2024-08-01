package com.example.demo.repository;

import com.example.demo.model.Reservation;
import com.example.demo.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT r FROM Reservation r WHERE r.room = :room AND r.slot.startTime < :endTime AND r.slot.endTime > :startTime")
    List<Reservation> findByRoomAndSlot(@Param("room") Room room, @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);

    @Query("SELECT r FROM Reservation r WHERE r.room = :room AND (r.slot.startTime < :endTime AND r.slot.endTime > :startTime)")
    List<Reservation> findAllByRoomAndSlotOverlaps(
            @Param("room") Room room,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime
    );
}
