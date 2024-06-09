package com.order.microservice.infrastructure.exceptions.order;

public class IncorrectStatusException extends RuntimeException {
    public IncorrectStatusException(int status) {
        super("There is no status with a number " + status);
    }
}
