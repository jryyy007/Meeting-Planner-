package com.example.demo.service;

import com.example.demo.exception.AvailabilityNotFoundException;
import com.example.demo.model.Availability;
import com.example.demo.repository.AvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvailabilityService {

    @Autowired
    private AvailabilityRepository availabilityRepository;

    public Availability saveAvailability(Availability availability) {
        return availabilityRepository.save(availability);
    }

    public Availability updateAvailability(Long availabilityId, Availability availabilityDetails) {
        Availability availability = availabilityRepository.findById(availabilityId)
                .orElseThrow(() -> new AvailabilityNotFoundException(availabilityId));
        availability.setRoom(availabilityDetails.getRoom());
        availability.setSlot(availabilityDetails.getSlot());
        return availabilityRepository.save(availability);
    }

    public void deleteAvailability(Long availabilityId) {
        availabilityRepository.deleteById(availabilityId);
    }

    public Availability getDetails(Long availabilityId) {
        return availabilityRepository.findById(availabilityId)
                .orElseThrow(() -> new AvailabilityNotFoundException(availabilityId));
    }

    public List<Availability> getAllAvailabilities() {
        return availabilityRepository.findAll();
    }
}
