package com.order.microservice.infrastructure.services;

import com.order.microservice.core.domain.Order;
import com.order.microservice.core.repository.IOrderRepository;
import com.order.microservice.core.service.IOrderService;
import com.order.microservice.core.service.IStationService;
import com.order.microservice.infrastructure.exceptions.order.IncorrectStatusException;
import com.order.microservice.infrastructure.exceptions.StationNotExistsException;
import com.order.microservice.infrastructure.exceptions.order.OrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements IOrderService {
    private final IOrderRepository orderRepository;
    private final IStationService stationService;

    @Autowired
    public OrderService(IOrderRepository orderRepository, IStationService stationService) {
        this.orderRepository = orderRepository;
        this.stationService = stationService;
    }
    @Override
    public int addOrder(int userId, int fromStationId, int toStationId) {
        if(!stationService.isExists(fromStationId)) {
            throw new StationNotExistsException(fromStationId);
        }
        if (!stationService.isExists(toStationId)){
            throw new StationNotExistsException(toStationId);
        }
        Order order = new Order(userId, fromStationId, toStationId);
        orderRepository.save(order);
        return order.getId();
    }

    @Override
    public List<Order> getByStatus(int status) {
        if(status < 1 || status > 3) {
            throw new IncorrectStatusException(status);
        }
        return orderRepository.findByStatus(status);
    }

    @Override
    public Order getById(int id, int userId) {
        Order order = orderRepository.findById(id);
        if (order == null) {
            throw new OrderNotFoundException(id);
        }
        if(order.getUserId() != userId){
            throw new OrderNotFoundException(id, userId);
        }
        return order;
    }

    @Override
    public void updateOrder(Order order) {
        orderRepository.save(order);
    }
}
