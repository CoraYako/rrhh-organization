package org.rrhh.employee.infrastructure.controller.dto;

import lombok.Builder;
import org.rrhh.department.infrastructure.dto.DepartmentResponseDTO;
import org.rrhh.organization.infrastructure.dto.OrganizationResponseDTO;

@Builder
public record EmployeeCompleteResponseDTO(
        String id,
        String firstName,
        String lastName,
        String email,
        String departmentCode,
        String organizationCode,
        DepartmentResponseDTO associatedDepartment,
        OrganizationResponseDTO associatedOrganization
) {
}
