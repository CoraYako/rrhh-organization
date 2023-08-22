package org.rrhh.organization.infrastructure.mapper;

import org.rrhh.organization.domain.document.Organization;
import org.rrhh.organization.infrastructure.dto.OrganizationResponseDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
public class OrganizationMapperImpl implements OrganizationMapper {

    @Override
    public Organization toDomain(OrganizationResponseDTO dto) {
        return Organization.builder()
                .id(dto.id())
                .name(dto.name())
                .description(dto.description())
                .code(dto.code())
                .creationDate(dto.creationDate())
                .build();
    }

    @Override
    public OrganizationResponseDTO toDTO(Organization domain) {
        return OrganizationResponseDTO.builder()
                .id(domain.getId().getValue())
                .name(domain.getName().getValue())
                .description(domain.getDescription().getValue())
                .code(domain.getCode().getValue())
                .creationDate(domain.getCreationDate().getValue())
                .build();
    }
}
