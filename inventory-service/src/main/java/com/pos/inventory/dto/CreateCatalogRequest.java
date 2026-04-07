package com.pos.inventory.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCatalogRequest {
    private String name;
    private String description;
    private List<String> imageUrls;
    private BigDecimal price;
    private String currency;
    private Availability availability;
    private String category;
}
