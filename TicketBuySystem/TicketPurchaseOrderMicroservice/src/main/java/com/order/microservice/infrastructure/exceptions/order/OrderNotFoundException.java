package com.order.microservice.infrastructure.exceptions.order;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(int id) {
        super("Order number " + id + " was not found");
    }
    public OrderNotFoundException(int id, int userId) {
        super("The user has number " + userId +", the order number" + id + "was not found");
    }
}
