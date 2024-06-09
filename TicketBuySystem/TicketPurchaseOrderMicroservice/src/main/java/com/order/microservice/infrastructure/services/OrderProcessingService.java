package com.order.microservice.infrastructure.services;

import com.order.microservice.core.domain.Order;
import com.order.microservice.core.repository.IOrderRepository;
import com.order.microservice.core.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderProcessingService {

    private final IOrderService orderService;

    @Autowired
    public OrderProcessingService(IOrderService orderService) {
        this.orderService = orderService;
    }

    @Transactional
    public void processOrders() {
        List<Order> ordersToCheck = orderService.getByStatus(1);

        for (Order order : ordersToCheck) {
            try {
                Thread.sleep((long) (Math.random() * 2000));

                int newStatus = Math.random() > 0.5 ? 2 : 3;
                order.setStatus(newStatus);
                orderService.updateOrder(order);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

