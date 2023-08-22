package org.rrhh.employee.infrastructure.controller.dto;

import lombok.Builder;

@Builder
public record EmployeeBasicResponseDTO(
        String id,
        String firstName,
        String lastName,
        String email,
        String departmentCode,
        String organizationCode
) {
}
