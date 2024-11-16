package com.mohamed.blogball.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(
    info =
        @Info(
            contact =
                @Contact(
                    name = "Mohamed Boukthir",
                    email = "medboukthir7@gmail.com",
                    url = "https://github.com/MohamedBoukthir"),
            description = "Open API docs for an blog website.",
            title = "BlogBall API",
            version = "1.0.0"),
    security = {@SecurityRequirement(name = "bearerAuth")})
@SecurityScheme(
    name = "bearerAuth",
    description = "Cooke authentication",
    scheme = "bearer",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    in = SecuritySchemeIn.COOKIE)
public class OpenApiConfiguration {}
