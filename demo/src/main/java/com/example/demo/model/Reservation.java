package com.example.demo.model;

import lombok.Data;

import jakarta.persistence.*;

@Entity
@Data
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "slot_id")
    private Slot slot;

    private MeetingType type;
    private int numberOfPeople;

    // Getters and Setters will be generated by Lombok's @Data annotation
}
