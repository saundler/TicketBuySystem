package com.order.microservice.infrastructure.repositories.station;

import com.order.microservice.core.domain.Station;
import com.order.microservice.core.repository.IStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class StationRepositoryImp implements IStationRepository {
    private final StationRepository stationRepository;

    @Autowired
    public StationRepositoryImp(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    @Override
    public Station findById(int id) {
        return stationRepository.findById(id);
    }
}
