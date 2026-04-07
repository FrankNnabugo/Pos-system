package com.pos.inventory.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LocationResponse {
    private String id;
    private String name;
    private LocationType locationType;
    private String address;
    private String salesPerson;

}
