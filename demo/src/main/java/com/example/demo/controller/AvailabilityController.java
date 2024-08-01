package com.example.demo.controller;

import com.example.demo.model.Availability;
import com.example.demo.service.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/availabilities")
public class AvailabilityController {

    @Autowired
    private AvailabilityService availabilityService;

    @PostMapping
    public Availability createAvailability(@RequestBody Availability availability) {
        return availabilityService.saveAvailability(availability);
    }

    @DeleteMapping("/{availabilityId}")
    public void deleteAvailability(@PathVariable Long availabilityId) {
        availabilityService.deleteAvailability(availabilityId);
    }

    @GetMapping("/{availabilityId}")
    public Availability getAvailability(@PathVariable Long availabilityId) {
        return availabilityService.getDetails(availabilityId);
    }

    @GetMapping
    public List<Availability> getAllAvailabilities() {
        return availabilityService.getAllAvailabilities();
    }
}
