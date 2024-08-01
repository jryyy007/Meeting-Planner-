package com.example.demo.service;

import com.example.demo.model.Reservation;
import com.example.demo.model.Room;
import com.example.demo.model.Slot;
import com.example.demo.model.User;
import com.example.demo.model.MeetingType;
import com.example.demo.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

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
        Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
        if (reservation != null) {
            reservation.setRoom(reservationDetails.getRoom());
            reservation.setSlot(reservationDetails.getSlot());
            reservation.setType(reservationDetails.getType());
            reservation.setNumberOfPeople(reservationDetails.getNumberOfPeople());
            return reservationRepository.save(reservation);
        } else {
            return null;
        }
    }

    public Reservation getReservationDetails(Long reservationId) {
        Optional<Reservation> reservation = reservationRepository.findById(reservationId);
        return reservation.orElse(null);
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public List<Reservation> findByRoomAndSlot(Room room, Slot slot) {
        return reservationRepository.findAllByRoomAndSlotOverlaps(room.getId(), slot.getStartTime().minusHours(1), slot.getEndTime().plusHours(1));
    }
}
