package com.authenticator.microservice.infrastructure.repositories.user;

import com.authenticator.microservice.core.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findById(int id);
}
