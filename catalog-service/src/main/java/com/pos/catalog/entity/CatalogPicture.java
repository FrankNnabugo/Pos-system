package com.pos.catalog.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "catalog_pictures")
public class CatalogPicture {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "catalog_id")
    private String catalogId;

    private String imageUrls;

    @CreationTimestamp
    @Column(nullable = false)
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;
}
