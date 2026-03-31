package com.pos.inventory.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "stock_movement")
public class StockMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private String id;

    @Column(nullable = false)
    private String productId;

    private String quantity;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location fromLocation;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location toLocation;

    @Column(nullable = false)
    private String reason;

    private String orderId;

    private String paymentId;

    @Column(nullable = false)
    private String approvedBy;

    @CreationTimestamp
    @Column(nullable = false)
    private Instant createdAt;

}
