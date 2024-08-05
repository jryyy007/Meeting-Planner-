package com.example.demo.controller;

import com.example.demo.model.Availability;
import com.example.demo.service.AvailabilityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/availabilities")
public class AvailabilityController {

    private final AvailabilityService availabilityService;

    public AvailabilityController(AvailabilityService availabilityService) {
        this.availabilityService = availabilityService;
    }

    @PostMapping
    public ResponseEntity<Availability> createAvailability(@RequestBody Availability availability) {
        Availability savedAvailability = availabilityService.saveAvailability(availability);
        return new ResponseEntity<>(savedAvailability, HttpStatus.CREATED);
    }

    @DeleteMapping("/{availabilityId}")
    public ResponseEntity<Void> deleteAvailability(@PathVariable Long availabilityId) {
        availabilityService.deleteAvailability(availabilityId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{availabilityId}")
    public ResponseEntity<Availability> getAvailability(@PathVariable Long availabilityId) {
        Availability availability = availabilityService.getDetails(availabilityId);
        return ResponseEntity.ok(availability);
    }

    @GetMapping
    public ResponseEntity<List<Availability>> getAllAvailabilities() {
        List<Availability> availabilities = availabilityService.getAllAvailabilities();
        return ResponseEntity.ok(availabilities);
    }
}
