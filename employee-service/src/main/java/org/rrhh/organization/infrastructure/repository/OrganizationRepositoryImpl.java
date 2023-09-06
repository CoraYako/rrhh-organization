package org.rrhh.organization.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import org.rrhh.organization.domain.document.Organization;
import org.rrhh.organization.domain.repository.OrganizationRepository;
import org.rrhh.organization.infrastructure.dto.OrganizationResponseDTO;
import org.rrhh.organization.infrastructure.mapper.OrganizationMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrganizationRepositoryImpl implements OrganizationRepository {

    private final OrganizationAPIClient organizationAPIClient;
    private final OrganizationMapper organizationMapper;

    @Override
    public Organization findByCode(String code) {
        ResponseEntity<OrganizationResponseDTO> response = organizationAPIClient.getOrganizationByCode(code);
        return organizationMapper.toDomain(response.getBody());
    }
}
