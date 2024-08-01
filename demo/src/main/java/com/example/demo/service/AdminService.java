package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private RoomRepository roomRepository;

    public void createRoom(Room room) {
        roomRepository.save(room);
    }

    public void deleteRoom(Room room) {
        roomRepository.delete(room);
    }

    public void manageEquipment(Equipment equipment) {
        // Implementation for managing equipment
    }
}
