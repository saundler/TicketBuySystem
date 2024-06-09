package com.authenticator.microservice.infrastructure.repositories.session;

import com.authenticator.microservice.core.domain.Session;
import com.authenticator.microservice.core.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    Session findByToken(String token);
    Session findTopByUserIdOrderByExpiresDesc(int userId);
    Collection<Session> findByUserId(int userId);
}
