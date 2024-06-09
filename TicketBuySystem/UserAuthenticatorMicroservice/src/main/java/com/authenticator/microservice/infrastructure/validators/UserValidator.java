package com.authenticator.microservice.infrastructure.validators;

import com.authenticator.microservice.core.domain.User;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.regex.Pattern;

@Component
public class UserValidator {

    private final Validator validator;

    public UserValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    public void validate(User user) {
        StringBuilder sb = new StringBuilder();

        if (user.getNickname() == null || user.getNickname().trim().isEmpty()) {
            sb.append("Nickname is required, ");
        }

        if (user.getEmail() == null || !isValidEmail(user.getEmail())) {
            sb.append("Email must be a valid email address, ");
        }

        if (user.getPassword() == null || !isValidPassword(user.getPassword())) {
            sb.append("Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one number, and one special character, ");
        }

        if (sb.length() > 0) {
            throw new ConstraintViolationException("Validation failed: " + sb.toString(), Set.of());
        }
    }

    private boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".");
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 8 &&
                Pattern.compile("[a-z]").matcher(password).find() &&
                Pattern.compile("[A-Z]").matcher(password).find() &&
                Pattern.compile("[0-9]").matcher(password).find() &&
                Pattern.compile("[@$!%*?&]").matcher(password).find();
    }
}