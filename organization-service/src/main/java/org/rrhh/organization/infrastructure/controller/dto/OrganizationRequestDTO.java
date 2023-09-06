package org.rrhh.organization.infrastructure.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Schema(description = "DTO model used as request to register an organization, all the information is required")
@Builder
public record OrganizationRequestDTO(
        @Schema(description = "Organization ID", example = "Salesforce")
        @NotEmpty @NotBlank String name,
        @Schema(description = "Organization Full Description", example = "Helps businesses keep track of " +
                "customer interactions and sales data")
        @NotEmpty @NotBlank String description,
        @Schema(description = "Unique Organization Code", example = "SF001")
        @NotEmpty @NotBlank String code
) {
}
