package com.order.microservice.infrastructure.repositories.order;

import com.order.microservice.core.domain.Order;
import com.order.microservice.core.repository.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepositoryImp implements IOrderRepository {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderRepositoryImp(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void save(Order user) {
        orderRepository.save(user);
    }

    @Override
    public Order findById(int id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> findByStatus(int status) {
        return orderRepository.findByStatus(status);
    }
}
