package org.rrhh.organization.infrastructure.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record OrganizationRequestDTO(
        @NotEmpty @NotBlank String name,
        @NotEmpty @NotBlank String description,
        @NotEmpty @NotBlank String code
) {
}
