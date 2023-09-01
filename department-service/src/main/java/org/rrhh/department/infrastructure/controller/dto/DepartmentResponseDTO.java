package org.rrhh.department.infrastructure.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(description = "DTO model used as response")
@Builder
public record DepartmentResponseDTO(
        @Schema(description = "Department ID", example = "64e7f827a64c725ba9cab945")
        String id,
        @Schema(description = "Department Name", example = "Information Technology Department")
        String name,
        @Schema(description = "A brief description of the current Department",
                example = "It is responsible for the management of information " +
                "that the business, related to the Internet, computing and technology.")
        String description,
        @Schema(description = "Unique Department Code", example = "IT001")
        String code
) {
}
