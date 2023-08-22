package org.rrhh.organization.infrastructure.controller.dto;

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
