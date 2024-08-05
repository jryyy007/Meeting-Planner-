package com.example.demo.service;

import com.example.demo.model.Availability;
import com.example.demo.repository.AvailabilityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AvailabilityService {

    private final AvailabilityRepository availabilityRepository;

    public AvailabilityService(AvailabilityRepository availabilityRepository) {
        this.availabilityRepository = availabilityRepository;
    }

    public Availability saveAvailability(Availability availability) {
        return availabilityRepository.save(availability);
    }

    public Availability updateAvailability(Long availabilityId, Availability availabilityDetails) {
        return availabilityRepository.findById(availabilityId)
                .map(availability -> {
                    availability.setRoom(availabilityDetails.getRoom());
                    availability.setSlot(availabilityDetails.getSlot());
                    return availabilityRepository.save(availability);
                })
                .orElseThrow(() -> new IllegalArgumentException("Availability not found with id: " + availabilityId));
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
        return availabilityRepository.findById(availabilityId)
                .orElseThrow(() -> new IllegalArgumentException("Availability not found with id: " + availabilityId));
    }
}
