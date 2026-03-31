package com.pos.inventory.dto;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryProductEvent {
    private String eventId;
    private String catalogId;
    CreateCatalogRequest createCatalogRequest;
    private Instant date;
}
