package com.pos.catalog.entity;

import com.pos.catalog.common.dto.Availability;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Catalog {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String category;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "picture_id")
    private List<CatalogPicture> picture;

    @Column(precision = 19, scale = 2, nullable = false)
    private BigDecimal price;

    private String currency;

    @Enumerated(EnumType.STRING)
    private Availability availability;

    @CreationTimestamp
    @Column(nullable = false)
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

}
