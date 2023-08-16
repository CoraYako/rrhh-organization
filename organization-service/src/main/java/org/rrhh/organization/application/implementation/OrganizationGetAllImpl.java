package org.rrhh.organization.application.implementation;

import org.rrhh.organization.application.usecase.OrganizationGetAllUseCase;
import org.rrhh.organization.domain.document.Organization;
import org.rrhh.organization.domain.exception.EmptyListException;
import org.rrhh.organization.domain.repository.OrganizationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationGetAllImpl implements OrganizationGetAllUseCase {

    private final OrganizationRepository organizationRepository;

    public OrganizationGetAllImpl(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    @Override
    public List<Organization> getOrganizations() {
        List<Organization> organizations = organizationRepository.findAll();
        if (organizations.isEmpty())
            throw new EmptyListException("No organizations found");
        return organizations;
    }
}
