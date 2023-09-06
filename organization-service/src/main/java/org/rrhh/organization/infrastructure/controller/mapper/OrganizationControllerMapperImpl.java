package org.rrhh.organization.infrastructure.controller.mapper;

import org.rrhh.organization.domain.document.Organization;
import org.rrhh.organization.infrastructure.controller.dto.OrganizationRequestDTO;
import org.rrhh.organization.infrastructure.controller.dto.OrganizationResponseDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
public class OrganizationControllerMapperImpl implements GenericMapper<OrganizationResponseDTO, Organization, OrganizationRequestDTO> {

    @Override
    public Organization toDomain(OrganizationRequestDTO dto) {
        return Organization.builder()
                .name(dto.name())
                .description(dto.description())
                .code(dto.code())
                .build();
    }

    @Override
    public OrganizationResponseDTO toDTO(Organization document) {
        return OrganizationResponseDTO.builder()
                .id(document.getId().getValue())
                .name(document.getName().getValue())
                .description(document.getDescription().getValue())
                .code(document.getCode().getValue())
                .creationDate(document.getCreationDate().getValue())
                .build();
    }
}
