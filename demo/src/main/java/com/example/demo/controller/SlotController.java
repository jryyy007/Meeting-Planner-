package com.example.demo.controller;

import com.example.demo.model.Slot;
import com.example.demo.service.SlotService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/slots")
public class SlotController {

    private final SlotService slotService;

    public SlotController(SlotService slotService) {
        this.slotService = slotService;
    }

    @PostMapping
    public ResponseEntity<Slot> createSlot(@RequestBody Slot slot) {
        Slot savedSlot = slotService.saveSlot(slot);
        return new ResponseEntity<>(savedSlot, HttpStatus.CREATED);
    }

    @PutMapping("/{slotId}")
    public ResponseEntity<Slot> updateSlot(@PathVariable Long slotId, @RequestBody Slot slotDetails) {
        Slot updatedSlot = slotService.updateSlot(slotId, slotDetails);
        return ResponseEntity.ok(updatedSlot);
    }

    @DeleteMapping("/{slotId}")
    public ResponseEntity<Void> deleteSlot(@PathVariable Long slotId) {
        slotService.deleteSlot(slotId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{slotId}")
    public ResponseEntity<Slot> getSlot(@PathVariable Long slotId) {
        Slot slot = slotService.getDetails(slotId);
        return ResponseEntity.ok(slot);
    }

    @GetMapping
    public ResponseEntity<List<Slot>> getAllSlots() {
        List<Slot> slots = slotService.getAllSlots();
        return ResponseEntity.ok(slots);
    }
}
