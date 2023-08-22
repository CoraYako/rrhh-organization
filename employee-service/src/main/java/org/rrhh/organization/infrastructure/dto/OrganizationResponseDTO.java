package org.rrhh.organization.infrastructure.dto;

import lombok.Builder;

@Builder
public record OrganizationResponseDTO(
        String id,
        String name,
        String description,
        String code,
        String creationDate
) {
}
