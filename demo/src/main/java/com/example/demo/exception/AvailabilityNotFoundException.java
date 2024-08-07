package com.example.demo.exception;

public class AvailabilityNotFoundException extends RuntimeException {
    public AvailabilityNotFoundException(Long id) {
        super("Availability not found with id: " + id);
    }
}
