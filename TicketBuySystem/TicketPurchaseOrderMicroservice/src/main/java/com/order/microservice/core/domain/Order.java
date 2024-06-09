package com.order.microservice.core.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "from_station_id")
    private int fromStationId;

    @Column(name = "to_station_id")
    private int toStationId;

    @Column(name = "status")
    private int status;

    @Column(name = "created")
    private LocalDateTime created;

    public Order(int userId, int fromStationId, int toStationId) {
        this.userId = userId;
        this.fromStationId = fromStationId;
        this.toStationId = toStationId;
        this.status = 1;
        this.created = LocalDateTime.now();
    }
}
