package com.order.microservice.infrastructure.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Collections;
import java.util.Date;

@Service
public class JwtService {
    private String secretKey = "my0320character0ultra0secure0and0ultra0long0secret";

    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().verifyWith(signingKey())
                .build().parseSignedClaims(token).getPayload();
    }

    public UsernamePasswordAuthenticationToken getAuthentication(String token) {
        Claims claims = getAllClaimsFromToken(token);
        String username = claims.getSubject();
        String password = (String) claims.get("password");

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                username,
                password,
                Collections.singleton(new SimpleGrantedAuthority("DEFAULT_ROLE"))
        );

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    public boolean isTokenValid(String token) {
        try {
            Claims claims = getAllClaimsFromToken(token);
            return !claims.getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    private SecretKey signingKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }
}
