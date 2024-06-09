package com.authenticator.microservice.core.service;

import com.authenticator.microservice.core.domain.Session;
import com.authenticator.microservice.core.domain.User;

import java.time.LocalDateTime;

public interface ISessionService {
    void addSession(int userId, String token, LocalDateTime expires);
    boolean isTokenValid(Session session);

    boolean isTokenValid(String token);

    public int getUserId(String token);
    Session getLatestSessionByUserId(int userId);
}
