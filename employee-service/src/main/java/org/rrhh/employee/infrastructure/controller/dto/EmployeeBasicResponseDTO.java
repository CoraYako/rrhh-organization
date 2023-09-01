package org.rrhh.employee.infrastructure.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(description = "Basic DTO model used as response for list employees")
@Builder
public record EmployeeBasicResponseDTO(
        @Schema(description = "Employee ID", example = "64e7f827a64c725ba9cab945")
        String id,
        @Schema(description = "First Name", example = "Olivia")
        String firstName,
        @Schema(description = "Last Name", example = "Lutero")
        String lastName,
        @Schema(description = "Employee Email", example = "olivia@email.com")
        String email,
        @Schema(description = "Department Code", example = "HR002")
        String departmentCode,
        @Schema(description = "Organization Code", example = "SF001")
        String organizationCode
) {
}
