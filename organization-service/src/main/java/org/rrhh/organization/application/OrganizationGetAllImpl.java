package org.rrhh.organization.application;

import org.rrhh.organization.domain.document.Organization;
import org.rrhh.organization.domain.repository.OrganizationRepository;
import org.rrhh.organization.domain.usecase.OrganizationGetAllUseCase;
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
        return organizationRepository.findAll();
    }
}
