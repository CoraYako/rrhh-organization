package org.rrhh.organization.infrastructure.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Schema(description = "DTO model used for list organizations with its information")
@Builder
public record OrganizationListDTO(
        @Schema(description = "Organization information")
        List<OrganizationResponseDTO> organizations
) {
}
