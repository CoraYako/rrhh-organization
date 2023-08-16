package org.rrhh.department.infrastructure.dto;

import lombok.Builder;

@Builder
public record DepartmentResponseDTO(
        String id,
        String name,
        String description,
        String code
) {
}
