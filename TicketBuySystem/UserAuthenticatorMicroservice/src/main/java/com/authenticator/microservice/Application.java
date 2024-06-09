package com.authenticator.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.authenticator.microservice")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

