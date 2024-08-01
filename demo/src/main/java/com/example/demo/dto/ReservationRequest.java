package com.example.demo.dto;

import com.example.demo.model.MeetingType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReservationRequest {
        private Long userId;
        private Long roomId;
        private Long slotId;
        private MeetingType type;
        private int numberOfPeople;
}
