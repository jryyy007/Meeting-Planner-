package com.example.demo.controller;

import com.example.demo.dto.ManageEquipmentRequest;
import com.example.demo.model.Admin;
import com.example.demo.model.Room;
import com.example.demo.model.Equipment;
import com.example.demo.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) {
        Admin savedAdmin = adminService.saveAdmin(admin);
        return new ResponseEntity<>(savedAdmin, HttpStatus.CREATED);
    }

    @PutMapping("/{adminId}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable Long adminId, @RequestBody Admin adminDetails) {
        Admin updatedAdmin = adminService.updateAdmin(adminId, adminDetails);
        return ResponseEntity.ok(updatedAdmin);
    }

    @DeleteMapping("/{adminId}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long adminId) {
        adminService.deleteAdmin(adminId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{adminId}")
    public ResponseEntity<Admin> getAdmin(@PathVariable Long adminId) {
        Admin admin = adminService.getAdminDetails(adminId);
        return ResponseEntity.ok(admin);
    }

    @GetMapping
    public ResponseEntity<List<Admin>> getAllAdmins() {
        List<Admin> admins = adminService.getAllAdmins();
        return ResponseEntity.ok(admins);
    }

    @PostMapping("/room")
    public ResponseEntity<Room> createRoom(@RequestBody Room room) {
        Room savedRoom = adminService.createRoom(room);
        return new ResponseEntity<>(savedRoom, HttpStatus.CREATED);
    }

    @DeleteMapping("/room/{roomId}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long roomId) {
        adminService.deleteRoom(roomId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/equipment")
    public ResponseEntity<Void> manageEquipment(@RequestBody ManageEquipmentRequest request) {
        adminService.manageEquipment(request.getRoomId(), request.getEquipment());
        return ResponseEntity.ok().build();
    }
}
