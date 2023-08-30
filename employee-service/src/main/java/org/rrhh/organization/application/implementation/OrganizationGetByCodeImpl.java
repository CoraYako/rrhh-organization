package org.rrhh.organization.application.implementation;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.rrhh.organization.application.usecase.OrganizationGetByCodeUseCase;
import org.rrhh.organization.domain.document.Organization;
import org.rrhh.organization.domain.repository.OrganizationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class OrganizationGetByCodeImpl implements OrganizationGetByCodeUseCase {

    private final Logger LOGGER = LoggerFactory.getLogger(OrganizationGetByCodeImpl.class);
    private final OrganizationRepository organizationRepository;

    public OrganizationGetByCodeImpl(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    /*
    To use the Retry pattern, uncomment the @Retry that allows to call the external microservice
    a certain numbers of times before it ends up in the fallback method.

    @Retry(name = "${spring.application.name}", fallbackMethod = OrganizationConstants.METHOD_NAME)
    */
    @Override
    @CircuitBreaker(name = "${spring.application.name}")
    public Organization getOrganizationByCode(String code) {
        LOGGER.info("#####----- inside getOrganizationByCode() method -----#####");

        return organizationRepository.findByCode(code);
    }

    /*
    To use the below method, add the following code inside the parentheses of @CircuitBreaker or @Retry annotation.
    Use a comma to separate from other specifications:
    fallbackMethod = DepartmentConstants.METHOD_NAME
    */
    public Organization getDefaultOrganization(String code, Exception exception) {
        LOGGER.error("#####----- inside getDefaultOrganization() method -----#####");
        LOGGER.error(exception.getLocalizedMessage());

        DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String creationDate = FORMATTER.format(LocalDateTime.now());

        return Organization.builder()
                .id(null)
                .name("Default Organization")
                .description("Fake Organization fallback")
                .code(code)
                .creationDate(creationDate)
                .build();
    }
}
