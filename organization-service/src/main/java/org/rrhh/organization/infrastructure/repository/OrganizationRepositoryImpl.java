package org.rrhh.organization.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import org.rrhh.organization.domain.document.Organization;
import org.rrhh.organization.domain.repository.OrganizationRepository;
import org.rrhh.organization.infrastructure.repository.data.OrganizationPersistence;
import org.rrhh.organization.infrastructure.repository.document.OrganizationDocument;
import org.rrhh.organization.infrastructure.repository.mapper.OrganizationMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OrganizationRepositoryImpl implements OrganizationRepository {

    private final OrganizationPersistence organizationPersistence;
    private final OrganizationMapper organizationMapper;

    @Override
    public Organization save(Organization organization) {
        OrganizationDocument organizationDocument = organizationMapper.toDocument(organization);
        organizationDocument = organizationPersistence.save(organizationDocument);
        return organizationMapper.toDomain(organizationDocument);
    }

    @Override
    public Optional<Organization> findByCode(String code) {
        Optional<OrganizationDocument> optionalOrganizationDocument = organizationPersistence.findByCode(code);
        return optionalOrganizationDocument.map(organizationMapper::toDomain);
    }

    @Override
    public List<Organization> findAll() {
        return organizationPersistence.findAll().stream()
                .map(organizationMapper::toDomain)
                .toList();
    }

    @Override
    public boolean existsByName(String name) {
        return organizationPersistence.existsByName(name);
    }

    @Override
    public boolean existsByCode(String code) {
        return organizationPersistence.existsByCode(code);
    }
}
