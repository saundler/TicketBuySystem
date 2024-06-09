package com.authenticator.microservice.core.repository;

import com.authenticator.microservice.core.domain.User;

public interface IUserRepository {
    void save(User user);
    User findByEmail(String email);
    User findById(int id);
}
