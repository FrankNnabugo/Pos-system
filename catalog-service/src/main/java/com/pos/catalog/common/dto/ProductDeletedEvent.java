package com.pos.catalog.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDeletedEvent {
    private String eventId;
    private String catalogId;
    CreateCatalogRequest createCatalogRequest;
    private Instant date;
}
