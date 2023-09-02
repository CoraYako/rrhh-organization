package org.rrhh.organization;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        info = @Info(
                title = "Organization Service REST API",
                description = "Organization Service Documentation",
                version = "v1.0",
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
public class OrganizationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrganizationServiceApplication.class, args);
    }

}
