package com.blog.application.configurations;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
//    @Bean
//    public OpenAPI customOpenAPI() {
//        final String securitySchemeName = "bearerAuth";
//        return new OpenAPI()
//                .info(new Info()
//                        .title("Blog Application API")
//                        .version("1.0")
//                        .description("Backend APIs for the Blog Application project developed."))
//                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
//                .components(new Components()
//                        .addSecuritySchemes(securitySchemeName,
//                                new SecurityScheme()
//                                        .name(securitySchemeName)
//                                        .type(SecurityScheme.Type.HTTP)
//                                        .scheme("bearer")
//                                        .bearerFormat("JWT")));
//    }

    public static final String AUTHORIZATION_HEADER = "Authorization";

    @Bean
    public OpenAPI api() {

        final String securitySchemeName = "JWT";

        return new OpenAPI()
                .info(getInfo())

                // Equivalent of securityContexts()
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))

                // Equivalent of securitySchemes(Arrays.asList(apiKeys()))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(AUTHORIZATION_HEADER)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));
    }

    private Info getInfo() {

        return new Info()
                .title("Blogging Application : Api Documentation")
                .description("This project is practicing by shariar to learn spring boot")
                .version("1.0")
                .termsOfService("This project is using spring boot")
                .contact(new Contact()
                        .name("Shahriar")
                        .url("https://blogging.com")
                        .email("blog@gmail.com"))
                .license(new License()
                        .name("License of APIS")
                        .url("API license URL"));
    }

}
