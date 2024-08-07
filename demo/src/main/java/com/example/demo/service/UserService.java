package com.example.demo.service;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.Reservation;
import com.example.demo.model.Room;
import com.example.demo.model.Slot;
import com.example.demo.model.User;
import com.example.demo.model.MeetingType;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final ReservationService reservationService;
    private final RoomService roomService;

    public UserService(UserRepository userRepository, ReservationService reservationService, RoomService roomService) {
        this.userRepository = userRepository;
        this.reservationService = reservationService;
        this.roomService = roomService;
    }

    public Reservation reserveRoom(User user, Room room, Slot slot, MeetingType type, int numberOfPeople) {
        if (!roomService.isAvailable(room, slot)) {
            throw new IllegalArgumentException("Room is not available for the selected slot.");
        }
        return reservationService.reserveRoom(user, room, slot, type, numberOfPeople);
    }

    public void cancelReservation(Long reservationId) {
        Reservation reservation = reservationService.getReservationDetails(reservationId);
        if (reservation != null) {
            reservationService.cancelReservation(reservationId);
        } else {
            throw new IllegalArgumentException("Reservation not found with id: " + reservationId);
        }
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long userId, User userDetails) {
        return userRepository.findById(userId)
                .map(user -> {
                    user.setName(userDetails.getName());
                    user.setEmail(userDetails.getEmail());
                    user.setPassword(userDetails.getPassword());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public User getUserDetails(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
