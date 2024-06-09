package com.authenticator.microservice.infrastructure.exceptions.registration;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException() {
        super("A user with such an email already exists");
    }
}
