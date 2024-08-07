package com.example.demo.service;

import com.example.demo.exception.SlotNotFoundException;
import com.example.demo.model.Slot;
import com.example.demo.repository.SlotRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SlotService {

    private final SlotRepository slotRepository;

    public SlotService(SlotRepository slotRepository) {
        this.slotRepository = slotRepository;
    }

    public Slot saveSlot(Slot slot) {
        return slotRepository.save(slot);
    }

    public Slot updateSlot(Long slotId, Slot slotDetails) {
        return slotRepository.findById(slotId)
                .map(slot -> {
                    slot.setStartTime(slotDetails.getStartTime());
                    slot.setEndTime(slotDetails.getEndTime());
                    return slotRepository.save(slot);
                })
                .orElseThrow(() -> new IllegalArgumentException("Slot not found with id: " + slotId));
    }

    public void deleteSlot(Long slotId) {
        slotRepository.deleteById(slotId);
    }

    public Slot getSlot(Long slotId) {
        return slotRepository.findById(slotId)
                .orElseThrow(() -> new SlotNotFoundException(slotId));
    }

    public List<Slot> getAllSlots() {
        return slotRepository.findAll();
    }

    public Slot getDetails(Long slotId) {
        return slotRepository.findById(slotId)
                .orElseThrow(() -> new IllegalArgumentException("Slot not found with id: " + slotId));
    }
}
