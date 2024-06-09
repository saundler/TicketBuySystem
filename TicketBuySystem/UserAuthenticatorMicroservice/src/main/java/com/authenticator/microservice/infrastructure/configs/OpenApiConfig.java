package com.authenticator.microservice.infrastructure.configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "Пример генерации OpenAPI из Spring MVC", version = "1.0.0"),
        security = @SecurityRequirement(name = "BearerAuth")
)
@SecurityScheme(
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT",
        name = "BearerAuth"
)
public class OpenApiConfig {}

