package com.order.microservice.infrastructure.services;

import com.order.microservice.core.domain.Station;
import com.order.microservice.core.repository.IStationRepository;
import com.order.microservice.core.service.IStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StationService implements IStationService {
    private final IStationRepository stationRepository;

    @Autowired
    public StationService(IStationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    @Override
    public boolean isExists(int id) {
        Station station = stationRepository.findById(id);
        return station != null;
    }
}
