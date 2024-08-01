package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/rooms")
    public void createRoom(@RequestBody Room room) {
        adminService.createRoom(room);
    }

    @DeleteMapping("/rooms/{roomId}")
    public void deleteRoom(@PathVariable Long roomId) {
        Room room = new Room(); // Fetch room from database
        adminService.deleteRoom(room);
    }
}
