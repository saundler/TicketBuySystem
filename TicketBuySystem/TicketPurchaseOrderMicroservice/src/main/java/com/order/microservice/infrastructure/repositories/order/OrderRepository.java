package com.order.microservice.infrastructure.repositories.order;

import com.order.microservice.core.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByStatus(int status);
    Order findById(int id);
}
