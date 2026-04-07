package com.pos.inventory.dto;

import com.pos.inventory.entity.Location;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryRequest {
    private String quantity;
    private String productId;
}
