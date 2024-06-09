package com.authenticator.microservice.core.repository;

import com.authenticator.microservice.core.domain.Session;

import java.util.Collection;

public interface ISessionRepository {
    void save(Session session);
    Session findByToken(String token);
    Session findLatestSessionByUserId(int userId);
}
