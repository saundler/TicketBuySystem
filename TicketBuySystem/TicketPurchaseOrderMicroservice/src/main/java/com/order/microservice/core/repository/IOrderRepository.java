package com.order.microservice.core.repository;

import com.order.microservice.core.domain.Order;

import java.util.List;

public interface IOrderRepository {
    void save(Order user);
    Order findById(int id);
    List<Order> findByStatus(int status);
}
