package com.order.microservice.core.service;

import com.order.microservice.core.domain.Order;

import java.util.List;

public interface IOrderService {
    int addOrder(int userId, int fromStationId, int toStationId);

    List<Order> getByStatus(int status);
    Order getById(int id, int userId);

    void updateOrder(Order order);
}
