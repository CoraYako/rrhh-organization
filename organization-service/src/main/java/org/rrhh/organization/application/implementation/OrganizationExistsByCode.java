package org.rrhh.organization.application.implementation;

import org.rrhh.organization.application.usecase.OrganizationExistsByCodeUseCase;
import org.rrhh.organization.domain.exception.NullParameterException;
import org.rrhh.organization.domain.exception.ResourceExistException;
import org.rrhh.organization.domain.repository.OrganizationRepository;
import org.springframework.stereotype.Service;

@Service
public class OrganizationExistsByCode implements OrganizationExistsByCodeUseCase {

    private final OrganizationRepository organizationRepository;

    public OrganizationExistsByCode(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    @Override
    public void existsByCode(String code) {
        if (code.trim().isEmpty())
            throw new NullParameterException("Organization code");
        if (organizationRepository.existsByCode(code))
            throw new ResourceExistException("Organization", "code", code);
    }
}
