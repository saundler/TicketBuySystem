package com.authenticator.microservice.core.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "sessions")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "token")
    private String token;

    @Column(name = "expires")
    private LocalDateTime expires;

    public Session(int userId, String token, LocalDateTime expires) {
        this.userId = userId;
        this.token = token;
        this.expires = expires;
    }
}
