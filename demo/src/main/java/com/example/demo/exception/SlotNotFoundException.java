package com.example.demo.exception;

public class SlotNotFoundException extends RuntimeException {
    public SlotNotFoundException(Long id) {
        super("Slot not found with id: " + id);
    }
}
