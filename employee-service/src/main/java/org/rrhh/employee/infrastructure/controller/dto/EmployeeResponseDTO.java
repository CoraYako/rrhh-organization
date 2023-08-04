package org.rrhh.employee.infrastructure.controller.dto;

import lombok.Builder;
import org.rrhh.department.infrastructure.dto.DepartmentResponseDTO;

@Builder
public record EmployeeResponseDTO(
        String id,
        String firstName,
        String lastName,
        String email,
        String departmentCode,
        DepartmentResponseDTO associatedDepartment
) {
}
