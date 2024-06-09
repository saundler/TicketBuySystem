package com.order.microservice.infrastructure.exceptions;

public class StationNotExistsException extends RuntimeException {
    public StationNotExistsException(int id) {
        super("There is no station with a number " + id);
    }
}
