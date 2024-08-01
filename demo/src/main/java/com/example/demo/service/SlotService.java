package com.example.demo.service;

import com.example.demo.model.Slot;
import com.example.demo.repository.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SlotService {

    @Autowired
    private SlotRepository slotRepository;

    public Slot getDetails(Long slotId) {
        return slotRepository.findById(slotId).orElse(null);
    }

    // Add other methods as needed
}
