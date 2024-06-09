package com.authenticator.microservice.infrastructure.repositories.user;

import com.authenticator.microservice.core.domain.User;
import com.authenticator.microservice.core.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserRepositoryImp implements IUserRepository {
    private final UserRepository userRepository;

    @Autowired
    public UserRepositoryImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return  userRepository.findByEmail(email);
    }

    @Override
    public User findById(int id) {
        return userRepository.findById(id);
    }
}
