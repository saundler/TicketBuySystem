package com.authenticator.microservice.infrastructure.services;

import com.authenticator.microservice.core.domain.User;
import com.authenticator.microservice.infrastructure.dto.UserDto;
import com.authenticator.microservice.core.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthService {
    private final JwtService jwtService;
    private final IUserService userService;

    @Autowired
    public AuthService(JwtService jwtService, IUserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    public String register(UserDto userDto) {
        User user = new User(userDto.nickname(), userDto.email(), userDto.password());

        userService.addUser(user);
        return jwtService.getToken(user);
    }

    public String authenticate(UserDto userDto) {
        User user = userService.getUserByEmail(userDto.email());
        try {
            if (Objects.equals(user.getPassword(), userDto.password())) {
                return jwtService.getToken(user);
            }
        } catch (Exception e) {
            throw new InsufficientAuthenticationException(e.getMessage());
        }
        throw new BadCredentialsException("Invalid password!");
    }
}
