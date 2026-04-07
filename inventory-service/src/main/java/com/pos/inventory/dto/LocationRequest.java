package com.pos.inventory.dto;

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
