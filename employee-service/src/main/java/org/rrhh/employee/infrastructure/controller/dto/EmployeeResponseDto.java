package org.rrhh.employee.infrastructure.controller.dto;

import lombok.Builder;

@Builder
public record EmployeeResponseDto(
        String id,
        String firstName,
        String lastName,
        String email
) {
}
