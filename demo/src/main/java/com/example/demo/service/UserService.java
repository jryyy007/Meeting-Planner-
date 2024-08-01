package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

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

    public void cancelReservation(Reservation reservation) {
        reservationRepository.delete(reservation);
    }
}
