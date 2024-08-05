package com.example.demo.service;

import com.example.demo.model.Admin;
import com.example.demo.model.Room;
import com.example.demo.model.Equipment;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AdminService {

    private final AdminRepository adminRepository;
    private final RoomRepository roomRepository;

    public AdminService(AdminRepository adminRepository, RoomRepository roomRepository) {
        this.adminRepository = adminRepository;
        this.roomRepository = roomRepository;
    }

    public Admin saveAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    public Admin updateAdmin(Long adminId, Admin adminDetails) {
        return adminRepository.findById(adminId)
                .map(admin -> {
                    admin.setName(adminDetails.getName());
                    admin.setEmail(adminDetails.getEmail());
                    admin.setPassword(adminDetails.getPassword());
                    return adminRepository.save(admin);
                })
                .orElseThrow(() -> new IllegalArgumentException("Admin not found with id: " + adminId));
    }

    public void deleteAdmin(Long adminId) {
        adminRepository.deleteById(adminId);
    }

    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }

    public void deleteRoom(Long roomId) {
        roomRepository.deleteById(roomId);
    }

    public Room manageEquipment(Long roomId, Equipment equipment) {
        return roomRepository.findById(roomId)
                .map(room -> {
                    room.getEquipment().add(equipment);
                    return roomRepository.save(room);
                })
                .orElseThrow(() -> new IllegalArgumentException("Room not found with id: " + roomId));
    }

    public Room getRoomById(Long roomId) {
        return roomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("Room not found with id: " + roomId));
    }

    public Admin getAdminDetails(Long adminId) {
        return adminRepository.findById(adminId)
                .orElseThrow(() -> new IllegalArgumentException("Admin not found with id: " + adminId));
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }
}
