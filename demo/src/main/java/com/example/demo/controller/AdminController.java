package com.example.demo.controller;

import com.example.demo.dto.ManageEquipmentRequest;
import com.example.demo.model.Admin;
import com.example.demo.model.Room;
import com.example.demo.model.Equipment;
import com.example.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping
    public Admin createAdmin(@RequestBody Admin admin) {
        return adminService.saveAdmin(admin);
    }

    @PutMapping("/{adminId}")
    public Admin updateAdmin(@PathVariable Long adminId, @RequestBody Admin adminDetails) {
        return adminService.updateAdmin(adminId, adminDetails);
    }

    @DeleteMapping("/{adminId}")
    public void deleteAdmin(@PathVariable Long adminId) {
        adminService.deleteAdmin(adminId);
    }

    @GetMapping("/{adminId}")
    public Admin getAdmin(@PathVariable Long adminId) {
        return adminService.getAdminDetails(adminId);
    }

    @GetMapping
    public List<Admin> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    @PostMapping("/room")
    public void createRoom(@RequestBody Room room) {
        adminService.createRoom(room);
    }

    @DeleteMapping("/room")
    public void deleteRoom(@RequestBody Room room) {
        adminService.deleteRoom(room);
    }

    @PostMapping("/equipment")
    public void manageEquipment(@RequestBody ManageEquipmentRequest request) {
        Long roomId = request.getRoomId();
        Equipment equipment = request.getEquipment();
        Room room = adminService.getRoomById(roomId);
        if (room != null) {
            adminService.manageEquipment(room, equipment);
        }
    }
}
