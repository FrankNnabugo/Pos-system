package com.pos.catalog.common.dto;

import com.pos.catalog.common.enums.Availability;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
