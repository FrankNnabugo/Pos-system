package com.pos.inventory.service;

import com.pos.inventory.dto.StockRequest;
import com.pos.inventory.dto.StockResponse;
import com.pos.inventory.entity.StockMovement;
import com.pos.inventory.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockMovementService {
    private final StockRepository stockRepository;
    private final ModelMapper modelMapper;

    public StockResponse recordStockMovement(StockRequest request){
        StockMovement stockMovement = stockRepository.save(toStockMovement(request));
        return modelMapper.map(stockMovement, StockResponse.class);

    }

    private StockMovement toStockMovement(StockRequest request){
        StockMovement stockMovement = new StockMovement();
        stockMovement.setProductId(request.getProductId());
        stockMovement.setQuantity(request.getQuantity());
        stockMovement.setFromLocation(request.getFromLocation());
        stockMovement.setToLocation(request.getToLocation());
        stockMovement.setOrderId(request.getOrderId());
        stockMovement.setPaymentId(request.getPaymentId());
        stockMovement.setApprovedBy(request.getApprovedBy());
        stockMovement.setReason(request.getReason());
        return stockMovement;
    }
}
