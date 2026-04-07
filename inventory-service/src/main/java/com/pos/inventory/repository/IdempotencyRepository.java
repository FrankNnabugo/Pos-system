package com.pos.inventory.repository;

import com.pos.inventory.entity.Idempotency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdempotencyRepository extends JpaRepository<Idempotency, String> {
    boolean existsByEventId(String eventId);
}
