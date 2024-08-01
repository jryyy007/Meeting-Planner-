package com.example.demo.service;

import com.example.demo.model.Admin;
import com.example.demo.model.Room;
import com.example.demo.model.Equipment;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private RoomRepository roomRepository;

    public Admin saveAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    public Admin updateAdmin(Long adminId, Admin adminDetails) {
        Admin admin = adminRepository.findById(adminId).orElse(null);
        if (admin != null) {
            admin.setName(adminDetails.getName());
            // Update other fields as needed
            return adminRepository.save(admin);
        } else {
            return null;
        }
    }

    public void deleteAdmin(Long adminId) {
        adminRepository.deleteById(adminId);
    }

    public void createRoom(Room room) {
        roomRepository.save(room);
    }

    public void deleteRoom(Room room) {
        roomRepository.delete(room);
    }

    public void manageEquipment(Room room, Equipment equipment) {
        room.getEquipment().add(equipment);
        roomRepository.save(room);
    }
    public Room getRoomById(Long roomId) {
        return roomRepository.findById(roomId).orElse(null);
    }


    public Admin getAdminDetails(Long adminId) {
        return adminRepository.findById(adminId).orElse(null);
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }
}
