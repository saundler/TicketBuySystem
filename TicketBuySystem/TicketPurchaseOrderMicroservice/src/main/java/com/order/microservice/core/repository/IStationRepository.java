package com.order.microservice.core.repository;

import com.order.microservice.core.domain.Station;

public interface IStationRepository {
    Station findById(int id);
}
