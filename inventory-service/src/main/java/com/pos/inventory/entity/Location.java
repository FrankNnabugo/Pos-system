package com.pos.inventory.entity;

import com.pos.inventory.dto.LocationType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "inventory_locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private String id;

    private String name; //warehouse 1 or store 1

    @Enumerated(EnumType.STRING)
    private LocationType locationType; //warehouse or store

    private String address; //7 Lagos Island, London street.

    @Column(nullable = false)
    private String salesPerson;

    @CreationTimestamp
    @Column(nullable = false)
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;
}
