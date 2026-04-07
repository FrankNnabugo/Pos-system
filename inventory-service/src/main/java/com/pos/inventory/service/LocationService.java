package com.pos.inventory.service;

import com.pos.inventory.dto.LocationRequest;
import com.pos.inventory.dto.LocationResponse;
import com.pos.inventory.entity.Location;
import com.pos.inventory.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public LocationResponse createInventoryLocation(LocationRequest request){
       Location savedLocation = locationRepository.save(toLocation(request));
       return modelMapper.map(savedLocation, LocationResponse.class);
    }

    private Location toLocation(LocationRequest request){
        Location location = new Location();
        location.setName(request.getName());
        location.setLocationType(request.getLocationType());
        location.setAddress(request.getAddress());
        location.setSalesPerson(request.getSalesPerson());
        return location;
    }
}
