package org.rrhh.employee.infrastructure.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import org.rrhh.department.infrastructure.dto.DepartmentResponseDTO;
import org.rrhh.organization.infrastructure.dto.OrganizationResponseDTO;

@Schema(description = "Complete Employee DTO model used as response when consulting Get Employee by ID")
@Builder
public record EmployeeCompleteResponseDTO(
        @Schema(description = "Employee ID", example = "64e7f827a64c725ba9cab945")
        String id,
        @Schema(description = "First Name", example = "Héctor")
        String firstName,
        @Schema(description = "Last Name", example = "Cortez")
        String lastName,
        @Schema(description = "Employee Email", example = "hector@email.com")
        String email,
        @Schema(description = "Department Code", example = "IT001")
        String departmentCode,
        @Schema(description = "Organization Code", example = "SF001")
        String organizationCode,
        @Schema(description = "Associated Department Information")
        DepartmentResponseDTO associatedDepartment,
        @Schema(description = "Associated Organization Information")
        OrganizationResponseDTO associatedOrganization
) {
}
