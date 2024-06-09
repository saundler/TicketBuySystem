package com.order.microservice.infrastructure.configs;

import com.order.microservice.infrastructure.services.OrderProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SchedulerConfig {

    private final OrderProcessingService orderProcessingService;

    @Autowired
    public SchedulerConfig(OrderProcessingService orderProcessingService) {
        this.orderProcessingService = orderProcessingService;
    }

    @Scheduled(fixedRate = 15000)
    public void scheduleOrderProcessing() {
        orderProcessingService.processOrders();
    }
}
