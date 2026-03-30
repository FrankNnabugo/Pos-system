package com.pos.inventory.common.dto;

import com.pos.inventory.common.enums.LocationType;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LocationRequest {
    private String name;
    private LocationType locationType;
    private String address;
    private String salesPerson;
}
