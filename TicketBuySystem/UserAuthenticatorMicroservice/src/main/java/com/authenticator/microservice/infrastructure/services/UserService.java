package com.authenticator.microservice.infrastructure.services;

import com.authenticator.microservice.core.domain.User;
import com.authenticator.microservice.core.repository.IUserRepository;
import com.authenticator.microservice.core.service.IUserService;
import com.authenticator.microservice.infrastructure.exceptions.registration.EmailAlreadyExistsException;
import com.authenticator.microservice.infrastructure.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    private final IUserRepository userRepository;
    private final UserValidator userDtoValidator;

    public UserService(@Autowired IUserRepository userRepository, @Autowired UserValidator userDtoValidator) {
        this.userRepository = userRepository;
        this.userDtoValidator = userDtoValidator;
    }

    @Override
    public void addUser(User user) {
        userDtoValidator.validate(user);

        try {
            getUserByEmail(user.getEmail());
            throw new EmailAlreadyExistsException();
        } catch (UsernameNotFoundException e) {
            userRepository.save(user);
        }
    }

    @Override
    public User getUserByEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("Email cannot be null");
        }
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return user;
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id);
    }
}
