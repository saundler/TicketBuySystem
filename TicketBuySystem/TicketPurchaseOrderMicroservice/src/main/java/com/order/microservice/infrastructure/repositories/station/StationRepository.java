package com.order.microservice.infrastructure.repositories.station;

import com.order.microservice.core.domain.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {
    Station findById(int id);
}
