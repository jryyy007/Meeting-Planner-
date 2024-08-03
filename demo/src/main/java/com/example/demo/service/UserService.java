package com.example.demo.service;

import com.example.demo.model.Reservation;
import com.example.demo.model.Room;
import com.example.demo.model.Slot;
import com.example.demo.model.User;
import com.example.demo.model.MeetingType;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private RoomService roomService;

    public Reservation reserveRoom(User user, Room room, Slot slot, MeetingType type, int numberOfPeople) {
        if (!roomService.isAvailable(room, slot)) {
            throw new IllegalArgumentException("Room is not available for the selected slot.");
        }
        return reservationService.reserveRoom(user, room, slot, type, numberOfPeople);
    }

    public void cancelReservation(Reservation reservation) {
        reservationService.cancelReservation(reservation.getId());
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long userId, User userDetails) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());
            user.setPassword(userDetails.getPassword());

            // Update other fields as needed
            return userRepository.save(user);
        } else {
            return null;
        }
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public User getUserDetails(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.orElse(null);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}