package com.authenticator.microservice.core.service;

import com.authenticator.microservice.core.domain.User;

public interface IUserService {
    void addUser(User user);
    User getUserByEmail(String email);
    User getUserById(int id);

}
