package org.rrhh.department.infrastructure.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(description = "DTO model used as associated Department response for Employee")
@Builder
public record DepartmentResponseDTO(
        @Schema(description = "Department ID", example = "64e7f827a64c725ba9cab945")
        String id,
        @Schema(description = "Department Name", example = "Human Resources")
        String name,
        @Schema(description = "Department Full Description", example = "Charged with finding, recruiting, screening," +
                " and training job applicants")
        String description,
        @Schema(description = "Unique Department Code", example = "HR001")
        String code
) {
}
