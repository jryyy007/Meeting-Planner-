package com.example.demo.service;

import com.example.demo.model.Availability;
import com.example.demo.repository.AvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvailabilityService {

    @Autowired
    private AvailabilityRepository availabilityRepository;

    public Availability saveAvailability(Availability availability) {
        return availabilityRepository.save(availability);
    }

    public Availability updateAvailability(Long availabilityId, Availability availabilityDetails) {
        Availability availability = availabilityRepository.findById(availabilityId).orElse(null);
        if (availability != null) {
            availability.setRoom(availabilityDetails.getRoom());
            availability.setSlot(availabilityDetails.getSlot());
            // Update other fields as needed
            return availabilityRepository.save(availability);
        } else {
            return null;
        }
    }

    public void deleteAvailability(Long availabilityId) {
        availabilityRepository.deleteById(availabilityId);
    }

    public Optional<Availability> getAvailability(Long availabilityId) {
        return availabilityRepository.findById(availabilityId);
    }

    public List<Availability> getAllAvailabilities() {
        return availabilityRepository.findAll();
    }

    public Availability getDetails(Long availabilityId) {
        return availabilityRepository.findById(availabilityId).orElse(null);
    }
}
