package com.order.microservice.infrastructure.dto;

import java.time.LocalDateTime;

public record UserDto(
        int id,
        String nickname,
        String email,
        String password,
        LocalDateTime created
) {}
