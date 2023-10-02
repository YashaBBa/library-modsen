package com.modsen.library.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;


@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Library API",
                description = "API provides endpoints for managing library-service",
                termsOfService = "Terms of service.",
                contact = @Contact(
                        name = "Borovtsov Yakov", email = "yashaborovcov@gmail.com"
                ),
                version = "v1"
        )
)
public class OpenApiConfig {
}
