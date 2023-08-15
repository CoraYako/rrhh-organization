package org.rrhh.organization.application.implementation;

import org.rrhh.organization.application.usecase.OrganizationFindByCodeUseCase;
import org.rrhh.organization.domain.document.Organization;
import org.rrhh.organization.domain.exception.NullParameterException;
import org.rrhh.organization.domain.exception.ResourceNotFoundException;
import org.rrhh.organization.domain.repository.OrganizationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrganizationFindByCode implements OrganizationFindByCodeUseCase {

    private final OrganizationRepository organizationRepository;

    public OrganizationFindByCode(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    @Override
    public Organization getOrganizationByCode(String code) {
        if (code.trim().isEmpty())
            throw new NullParameterException("Organization code");
        Optional<Organization> organizationOptional = organizationRepository.findByCode(code);
        return organizationOptional
                .orElseThrow(() -> new ResourceNotFoundException("Organization", "code", code));
    }
}
