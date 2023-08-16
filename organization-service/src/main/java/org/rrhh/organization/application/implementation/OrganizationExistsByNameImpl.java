package org.rrhh.organization.application.implementation;

import org.rrhh.organization.application.usecase.OrganizationExistsByNameUseCase;
import org.rrhh.organization.domain.exception.NullParameterException;
import org.rrhh.organization.domain.exception.ResourceExistException;
import org.rrhh.organization.domain.repository.OrganizationRepository;
import org.springframework.stereotype.Service;

@Service
public class OrganizationExistsByNameImpl implements OrganizationExistsByNameUseCase {

    private final OrganizationRepository organizationRepository;

    public OrganizationExistsByNameImpl(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    @Override
    public void existsOrganizationByName(String name) {
        if (name.trim().isEmpty())
            throw new NullParameterException("Organization name");
        if (organizationRepository.existsByName(name))
            throw new ResourceExistException("Organization", "name", name);
    }
}
