package org.rrhh.organization.infrastructure.controller.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record OrganizationResponseDTO(
        String id,
        String name,
        String description,
        String code,
        LocalDateTime creationDate
) {
}
