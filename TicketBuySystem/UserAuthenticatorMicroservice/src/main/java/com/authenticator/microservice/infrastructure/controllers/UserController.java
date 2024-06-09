package com.authenticator.microservice.infrastructure.controllers;

import com.authenticator.microservice.core.domain.User;
import com.authenticator.microservice.core.service.ISessionService;
import com.authenticator.microservice.infrastructure.dto.UserDto;
import com.authenticator.microservice.core.service.IUserService;
import com.authenticator.microservice.infrastructure.services.AuthService;
import com.authenticator.microservice.infrastructure.services.JwtService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final ISessionService sessionService;
    private final IUserService userService;
    private final AuthService authService;

    @Autowired
    public UserController(ISessionService sessionService, IUserService userService, JwtService jwtService, AuthService authService) {
        this.sessionService = sessionService;
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestParam String nickname,
            @RequestParam String email,
            @RequestParam String password) {
        try {
            UserDto userDto = new UserDto(nickname, email, password);
            String token = authService.register(userDto);
            return ResponseEntity.ok("User registrated successfully: " + token);
        } catch (Exception e) {
            return new ResponseEntity<>("Error registering user: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestParam String email,
            @RequestParam String password) {
        try {
            UserDto userDto = new UserDto(email, password);
            String token = authService.authenticate(userDto);
            return ResponseEntity.ok("User authenticated successfully: " + token);
        } catch (Exception e) {
            return new ResponseEntity<>("Error login user: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/info")
    public ResponseEntity<User> info(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        int userId = sessionService.getUserId(token);
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }
}
