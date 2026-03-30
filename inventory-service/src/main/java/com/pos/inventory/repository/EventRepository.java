package com.pos.inventory.repository;

import com.pos.inventory.entity.InventoryEvents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<InventoryEvents, String> {
    boolean existsByEventId(String eventId);
}
