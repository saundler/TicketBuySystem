package com.order.microservice.infrastructure.controllers;

import com.order.microservice.core.domain.Order;
import com.order.microservice.core.service.IOrderService;
import com.order.microservice.core.service.IStationService;
import com.order.microservice.infrastructure.dto.UserDto;
import com.order.microservice.infrastructure.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final IOrderService orderService;
    private final UserService userService;

    @Autowired
    public OrderController(IOrderService orderService, IStationService stationService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(
            @RequestParam int fromStationId,
            @RequestParam int toStationId,
            HttpServletRequest request) {
        try {
            String token = request.getHeader("Authorization").substring(7);
            UserDto userDto = userService.getUserByToken(token);
            int id = orderService.addOrder(userDto.id(), fromStationId, toStationId);
            return ResponseEntity.ok("Order created successfully. Order number: " + id);
        } catch (Exception e) {
            return new ResponseEntity<>("Error create order: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/info")
    public ResponseEntity<String> info(
            @RequestParam int id,
            HttpServletRequest request) {
        try {
            String token = request.getHeader("Authorization").substring(7);
            UserDto userDto = userService.getUserByToken(token);
            Order order = orderService.getById(id, userDto.id());
            return ResponseEntity.ok(String.valueOf(order));
        } catch (Exception e) {
            return new ResponseEntity<>("Error get info about order: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
