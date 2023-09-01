package org.rrhh.organization.infrastructure.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(description = "DTO model used as associated Organization response for Employee")
@Builder
public record OrganizationResponseDTO(
        @Schema(description = "Organization ID", example = "64e7f827a64c725ba9cab945")
        String id,
        @Schema(description = "Organization Name", example = "Salesforce")
        String name,
        @Schema(description = "Organization Full Description", example = "Helps businesses keep track of " +
                "customer interactions and sales data")
        String description,
        @Schema(description = "Unique Organization Code", example = "SF001")
        String code,
        @Schema(description = "Organization Creation Date in format DD/MM/YYYY", example = "08/06/14")
        String creationDate
) {
}
