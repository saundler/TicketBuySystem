package com.authenticator.microservice.infrastructure.services;

import com.authenticator.microservice.core.domain.Session;
import com.authenticator.microservice.core.domain.User;
import com.authenticator.microservice.core.service.ISessionService;
import com.authenticator.microservice.core.service.IUserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.ZoneId;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtService {
    private final ISessionService sessionService;
    private final IUserService userService;
    private String secretKey = "my0320character0ultra0secure0and0ultra0long0secret";

    @Autowired
    public JwtService(SessionService sessionService, IUserService userService) {
        this.sessionService = sessionService;
        this.userService = userService;
    }

    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().verifyWith(signingKey())
                .build().parseSignedClaims(token).getPayload();
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public UsernamePasswordAuthenticationToken getAuthentication(String token) {
        String username = getClaimFromToken(token, Claims::getSubject);
        User user = userService.getUserByEmail(username);
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority("DEFAULT_ROLE"))
        );
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    private String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("password", user.getPassword());
        String token = Jwts.builder()
                .setClaims(claims)
                .subject(user.getEmail())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
                .signWith(signingKey())
                .compact();

        sessionService.addSession(user.getId(), token, getAllClaimsFromToken(token).getExpiration().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());

        return token;
    }

    public String getToken(User user) {
        Session session = sessionService.getLatestSessionByUserId(user.getId());
        if (sessionService.isTokenValid(session)) {
            return session.getToken();
        }
        return generateToken(user);
    }

    private SecretKey signingKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }
}
