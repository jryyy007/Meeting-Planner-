package com.example.demo.service;

import com.example.demo.model.Slot;
import com.example.demo.repository.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SlotService {

    @Autowired
    private SlotRepository slotRepository;

    public Slot saveSlot(Slot slot) {
        return slotRepository.save(slot);
    }

    public Slot updateSlot(Long slotId, Slot slotDetails) {
        Slot slot = slotRepository.findById(slotId).orElse(null);
        if (slot != null) {
            slot.setStartTime(slotDetails.getStartTime());
            slot.setEndTime(slotDetails.getEndTime());
            // Update other fields as needed
            return slotRepository.save(slot);
        } else {
            return null;
        }
    }

    public void deleteSlot(Long slotId) {
        slotRepository.deleteById(slotId);
    }

    public Optional<Slot> getSlot(Long slotId) {
        return slotRepository.findById(slotId);
    }

    public List<Slot> getAllSlots() {
        return slotRepository.findAll();
    }

    public Slot getDetails(Long slotId) {
        return slotRepository.findById(slotId).orElse(null);
    }
}
