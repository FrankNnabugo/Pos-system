package com.pos.inventory.controller;

import com.pos.inventory.dto.ApiResponse;
import com.pos.inventory.dto.StockRequest;
import com.pos.inventory.dto.StockResponse;
import com.pos.inventory.service.StockMovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/inventory")
@RequiredArgsConstructor
public class StockMovement {
    private final StockMovementService stockMovementService;

    @PostMapping("/user/stock-movement")
    public ApiResponse<StockResponse> recordStockMovement(@RequestBody StockRequest request){
        StockResponse response = stockMovementService.recordStockMovement(request);
        return new ApiResponse<>(HttpStatus.OK.value(),HttpStatus.OK.name(), response);
    }
}
