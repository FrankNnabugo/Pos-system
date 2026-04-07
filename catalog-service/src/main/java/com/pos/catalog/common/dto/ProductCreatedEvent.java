package com.pos.catalog.common.dto;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreatedEvent {
    private String eventId;
    private String catalogId;
    CreateCatalogRequest createCatalogRequest;
    private Instant date;
}
