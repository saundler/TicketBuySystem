package com.order.microservice.core.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="stations")
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "station")
    private String station;

    public Station(String station) {
        this.station = station;
    }
}
