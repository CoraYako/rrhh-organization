package org.rrhh.organization.application.implementation;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.rrhh.organization.application.usecase.OrganizationGetByCodeUseCase;
import org.rrhh.organization.domain.OrganizationConstants;
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

    @Override
//    @Retry(name = "${spring.application.name}", fallbackMethod = OrganizationConstants.METHOD_NAME)
    @CircuitBreaker(name = "${spring.application.name}", fallbackMethod = OrganizationConstants.METHOD_NAME)
    public Organization getOrganizationByCode(String code) {
        LOGGER.info("#####----- inside getOrganizationByCode() method -----#####");

        return organizationRepository.findByCode(code);
    }

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
