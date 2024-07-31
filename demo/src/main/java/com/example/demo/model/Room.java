package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int capacity;

    @ElementCollection(targetClass = Equipment.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "room_equipment", joinColumns = @JoinColumn(name = "room_id"))
    @Column(name = "equipment")
    private List<Equipment> equipment;

    @OneToMany(mappedBy = "room")
    private List<Availability> availabilities;
}
