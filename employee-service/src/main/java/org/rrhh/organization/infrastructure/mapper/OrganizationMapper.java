package org.rrhh.organization.infrastructure.mapper;

import jakarta.validation.constraints.NotNull;
import org.rrhh.organization.domain.document.Organization;
import org.rrhh.organization.infrastructure.dto.OrganizationResponseDTO;

public interface OrganizationMapper {

    Organization toDomain(@NotNull OrganizationResponseDTO dto);

    OrganizationResponseDTO toDTO(@NotNull Organization domain);
}
