package com.pos.inventory.dto;

import com.pos.inventory.entity.Location;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StockResponse {
    private String id;
    private String productId;
    private String quantity;
    private Location fromLocation;
    private Location toLocation;
    private String orderId;
    private String paymentId;
    private String approvedBy;
    private String reason;
}
