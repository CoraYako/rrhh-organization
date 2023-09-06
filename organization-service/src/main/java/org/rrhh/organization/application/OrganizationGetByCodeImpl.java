package org.rrhh.organization.application;

import org.rrhh.organization.domain.document.Organization;
import org.rrhh.organization.domain.exception.NullParameterException;
import org.rrhh.organization.domain.exception.ResourceNotFoundException;
import org.rrhh.organization.domain.repository.OrganizationRepository;
import org.rrhh.organization.domain.usecase.OrganizationGetByCodeUseCase;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrganizationGetByCodeImpl implements OrganizationGetByCodeUseCase {

    private final OrganizationRepository organizationRepository;

    public OrganizationGetByCodeImpl(OrganizationRepository organizationRepository) {
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
