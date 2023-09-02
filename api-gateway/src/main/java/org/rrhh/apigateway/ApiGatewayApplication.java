package org.rrhh.apigateway;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;

@OpenAPIDefinition(
        info = @Info(
                title = "RRHH Microservices Project Documentation",
                version = "v1.0",
                description = "Use the dropdown button to see the the list of available APIs. " +
                        "Select one to see its complete documentation.",
                contact = @Contact(
                        name = "Héctor Armando Cortez",
                        email = "updown.input@gmail.com",
                        url = "https://www.linkedin.com/in/hector-cortez-cy/"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0.txt"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "GitHub source code",
                url = "https://github.com/CoraYako/rrhh-organization"
        ),
        servers = @Server(
                description = "Base URL for local instance",
                url = "http://localhost:9191"
        )
)
@SpringBootApplication
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder
                .routes()
                .route(route -> route
                        .path("/EMPLOYEE-SERVICE/v3/api-docs")
                        .and()
                        .method(HttpMethod.GET)
                        .uri("lb://EMPLOYEE-SERVICE"))
                .route(route -> route
                        .path("/DEPARTMENT-SERVICE/v3/api-docs")
                        .and()
                        .method(HttpMethod.GET)
                        .uri("lb://DEPARTMENT-SERVICE"))
                .route(route -> route
                        .path("/ORGANIZATION-SERVICE/v3/api-docs")
                        .and()
                        .method(HttpMethod.GET)
                        .uri("lb://ORGANIZATION-SERVICE"))
                .build();
    }
}
