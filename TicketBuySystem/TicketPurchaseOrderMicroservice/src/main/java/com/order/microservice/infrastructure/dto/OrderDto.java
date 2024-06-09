package com.order.microservice.infrastructure.dto;

public record OrderDto(
        int userId,
        int fromStationId,
        int toStationId
) {}
