package com.meliuy.teteraJoa.teteraJoa.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@SecurityScheme(
        name = "basicAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "basic"
)
public class OpenAPIConfig {

    @Value("${meli.openapi.dev-url}")
    private String devUrl;

    @Value("${meli.openapi.prod-url}")
    private String prodUrl;

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server()
                .url(devUrl)
                .description("Servidor de desarrollo");

        Server prodServer = new Server()
                .url(prodUrl)
                .description("Servidor de producción");

        Contact contact = new Contact()
                .email("sebita@gmail.com")
                .name("API");

        Info info = new Info()
                .title("Tutorial API")
                .version("2.0")
                .description("Este es un ejemplo simple de OpenAPI")
                .contact(contact);

        return new OpenAPI()
                .info(info)
                .servers(List.of(devServer, prodServer));
    }
}