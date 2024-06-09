package com.authenticator.microservice.infrastructure.services;

import com.authenticator.microservice.core.domain.Session;
import com.authenticator.microservice.core.repository.ISessionRepository;
import com.authenticator.microservice.core.service.ISessionService;
import com.authenticator.microservice.infrastructure.repositories.session.SessionRepositoryImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SessionService implements ISessionService {
    private final ISessionRepository sessionRepository;

    @Autowired
    public SessionService(SessionRepositoryImp sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public void addSession(int userId, String token, LocalDateTime expires) {
        Session session = new Session(userId, token, expires);
        sessionRepository.save(session);
    }

    @Override
    public Session getLatestSessionByUserId(int userId){
        return sessionRepository.findLatestSessionByUserId(userId);
    }

    @Override
    public boolean isTokenValid(Session session) {
        return session != null && session.getExpires().isAfter(LocalDateTime.now());
    }

    @Override
    public boolean isTokenValid(String token) {
        Session session = sessionRepository.findByToken(token);
        return session != null && session.getExpires().isAfter(LocalDateTime.now());
    }

    @Override
    public int getUserId(String token) {
        return sessionRepository.findByToken(token).getUserId();
    }
}
