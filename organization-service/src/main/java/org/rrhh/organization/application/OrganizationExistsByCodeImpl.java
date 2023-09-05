package org.rrhh.organization.application;

import org.rrhh.organization.domain.exception.NullParameterException;
import org.rrhh.organization.domain.exception.ResourceExistException;
import org.rrhh.organization.domain.repository.OrganizationRepository;
import org.rrhh.organization.domain.usecase.OrganizationExistsByCodeUseCase;
import org.springframework.stereotype.Service;

@Service
public class OrganizationExistsByCodeImpl implements OrganizationExistsByCodeUseCase {

    private final OrganizationRepository organizationRepository;

    public OrganizationExistsByCodeImpl(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    @Override
    public void existsOrganizationByCode(String code) {
        if (code.trim().isEmpty())
            throw new NullParameterException("Organization code");
        if (organizationRepository.existsByCode(code))
            throw new ResourceExistException("Organization", "code", code);
    }
}
