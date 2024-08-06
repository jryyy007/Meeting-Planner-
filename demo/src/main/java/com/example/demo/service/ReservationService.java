package com.example.demo.service;

import com.example.demo.model.Reservation;
import com.example.demo.model.Room;
import com.example.demo.model.Slot;
import com.example.demo.model.User;
import com.example.demo.model.MeetingType;
import com.example.demo.repository.ReservationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservation reserveRoom(User user, Room room, Slot slot, MeetingType type, int numberOfPeople) {
        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setRoom(room);
        reservation.setSlot(slot);
        reservation.setType(type);
        reservation.setNumberOfPeople(numberOfPeople);
        return reservationRepository.save(reservation);
    }

    public void cancelReservation(Long reservationId) {
        reservationRepository.deleteById(reservationId);
    }

    public Reservation updateReservation(Long reservationId, Reservation reservationDetails) {
        return reservationRepository.findById(reservationId)
                .map(reservation -> {
                    reservation.setRoom(reservationDetails.getRoom());
                    reservation.setSlot(reservationDetails.getSlot());
                    reservation.setType(reservationDetails.getType());
                    reservation.setNumberOfPeople(reservationDetails.getNumberOfPeople());
                    return reservationRepository.save(reservation);
                })
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found with id: " + reservationId));
    }

    public Reservation getReservationDetails(Long reservationId) {
        return reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found with id: " + reservationId));
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public List<Reservation> findByRoomAndSlot(Room room, Slot slot) {
        return reservationRepository.findAllByRoomAndSlotOverlaps(room.getId(), slot.getStartTime().minusHours(1), slot.getEndTime().plusHours(1));
    }
}
