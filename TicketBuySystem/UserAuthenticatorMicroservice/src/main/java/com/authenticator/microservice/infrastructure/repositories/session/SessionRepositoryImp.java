package com.authenticator.microservice.infrastructure.repositories.session;

import com.authenticator.microservice.core.domain.Session;
import com.authenticator.microservice.core.repository.ISessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Repository
public class SessionRepositoryImp implements ISessionRepository {
    private final SessionRepository sessionRepository;

    @Autowired
    public SessionRepositoryImp(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    @Transactional
    public void save(Session session) {
        sessionRepository.save(session);
    }

    @Override
    public Session findByToken(String token) {
        return sessionRepository.findByToken(token);
    }

    @Override
    public Session findLatestSessionByUserId(int userId) {
        return sessionRepository.findTopByUserIdOrderByExpiresDesc(userId);
    }
}
