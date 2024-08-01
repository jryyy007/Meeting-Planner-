package com.example.demo.controller;

import com.example.demo.model.Slot;
import com.example.demo.service.SlotService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/slots")
@AllArgsConstructor
public class SlotController {

    @Autowired
    private SlotService slotService;

    @PostMapping
    public Slot createSlot(@RequestBody Slot slot) {
        return slotService.saveSlot(slot);
    }

    @PutMapping("/{slotId}")
    public Slot updateSlot(@PathVariable Long slotId, @RequestBody Slot slotDetails) {
        return slotService.updateSlot(slotId, slotDetails);
    }

    @DeleteMapping("/{slotId}")
    public void deleteSlot(@PathVariable Long slotId) {
        slotService.deleteSlot(slotId);
    }

    @GetMapping("/{slotId}")
    public Slot getSlot(@PathVariable Long slotId) {
        return slotService.getDetails(slotId);
    }

    @GetMapping
    public List<Slot> getAllSlots() {
        return slotService.getAllSlots();
    }
}
