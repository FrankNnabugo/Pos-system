package com.pos.inventory.controller;

import com.pos.inventory.dto.ApiResponse;
import com.pos.inventory.dto.LocationRequest;
import com.pos.inventory.dto.LocationResponse;
import com.pos.inventory.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/inventory")
@RequiredArgsConstructor
public class InventoryLocation {
    private final LocationService locationService;

    @PostMapping("/user/inventory-location")
    public ApiResponse<LocationResponse> createInventoryLocation(@RequestBody LocationRequest request){
        LocationResponse response = locationService.createInventoryLocation(request);
        return new ApiResponse<>(HttpStatus.OK.value(),HttpStatus.OK.name(), response);
    }

}
