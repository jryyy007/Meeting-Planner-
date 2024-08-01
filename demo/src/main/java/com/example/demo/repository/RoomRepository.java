package com.example.demo.repository;

import com.example.demo.model.Equipment;
import com.example.demo.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    // Find rooms by capacity
    List<Room> findByCapacityGreaterThanEqual(int capacity);

    // Find rooms by equipment
    List<Room> findByEquipmentIn(List<Equipment> equipment);
}
