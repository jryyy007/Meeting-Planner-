package com.example.demo.repository;

import com.example.demo.model.Reservation;
import com.example.demo.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT r FROM Reservation r WHERE r.room.id = :roomId AND (r.slot.startTime BETWEEN :bufferStart AND :bufferEnd OR r.slot.endTime BETWEEN :bufferStart AND :bufferEnd)")
    List<Reservation> findAllByRoomAndSlotOverlaps(@Param("roomId") Long roomId,
                                                   @Param("bufferStart") LocalDateTime bufferStart,
                                                   @Param("bufferEnd") LocalDateTime bufferEnd);

    // Find reservations by room and time slot
    List<Reservation> findByRoomAndSlotStartTimeBetween(Room room, LocalDateTime startTime, LocalDateTime endTime);

    // Find reservations by user
    List<Reservation> findByUserId(Long userId);
}
