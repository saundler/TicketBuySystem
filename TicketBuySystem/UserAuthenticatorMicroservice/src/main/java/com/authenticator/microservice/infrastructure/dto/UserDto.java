package com.authenticator.microservice.infrastructure.dto;

public record UserDto(
        String nickname,
        String email,
        String password
) {
    public UserDto(String email, String password) {
        this(null, email, password);
    }
}
