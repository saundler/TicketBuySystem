package com.order.microservice.infrastructure.services;

import com.order.microservice.infrastructure.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class UserService {
    private final RestTemplate restTemplate;

    @Autowired
    public UserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;

    }
    public UserDto getUserByToken(String token) {
        String url = "http://authenticator-service:8081/api/user/info";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        // Создание сущности HTTP запроса
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Выполнение запроса
        ResponseEntity<UserDto> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                UserDto.class
        );
        return response.getBody();
    }
}
