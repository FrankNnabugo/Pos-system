package com.pos.inventory.service;

import com.pos.inventory.common.dto.InventoryProductEvent;
import com.pos.inventory.entity.Inventory;
import com.pos.inventory.entity.InventoryEvents;
import com.pos.inventory.repository.EventRepository;
import com.pos.inventory.repository.InventoryRepository;
import com.pos.inventory.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@KafkaListener(
        topics = "${spring.kafka.topics.catalog}",
        groupId = "inventory-service")
@Slf4j
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    private final EventRepository eventRepository;
    private final LocationRepository locationRepository;

    @KafkaHandler
    @Transactional
    public void createInventory(@Payload InventoryProductEvent event, String id, Acknowledgment ack){
        if(eventRepository.existsByEventId(event.getEventId())) return;
        inventoryRepository.save(toInventory(event, id));
        eventRepository.save(toEvent(event.getEventId()));
        ack.acknowledge();
    }

    private Inventory toInventory(InventoryProductEvent event, String id){
        Inventory inventory = new Inventory();
        inventory.setProductId(event.getCatalogId());
        inventory.setAvailableQuantity(0);
        inventory.setLocation(locationRepository.findById(id).orElseThrow());
        inventory.setReservedQuantity(0);
        return inventory;
    }

    private InventoryEvents toEvent(String eventId){
        InventoryEvents inventoryEvents = new InventoryEvents();
        inventoryEvents.setEventId(eventId);
        return inventoryEvents;
    }

}
