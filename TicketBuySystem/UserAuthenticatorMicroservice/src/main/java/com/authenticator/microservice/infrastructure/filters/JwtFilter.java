package com.authenticator.microservice.infrastructure.filters;

import com.authenticator.microservice.core.domain.User;
import com.authenticator.microservice.core.service.ISessionService;
import com.authenticator.microservice.core.service.IUserService;
import com.authenticator.microservice.infrastructure.services.JwtService;
import com.authenticator.microservice.infrastructure.services.SessionService;
import com.authenticator.microservice.infrastructure.services.UserService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.logging.Logger;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private static final Logger logger = Logger.getLogger(JwtFilter.class.getName());
    private final JwtService jwtService;
    private final ISessionService sessionService;

    @Autowired
    public JwtFilter(JwtService jwtService, ISessionService sessionService) {
        this.jwtService = jwtService;
        this.sessionService = sessionService;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        if (sessionService.isTokenValid(token)) {
            UsernamePasswordAuthenticationToken authentication = jwtService.getAuthentication(token);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}
