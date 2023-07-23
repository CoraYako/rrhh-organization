package org.rrhh.department.infrastructure.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record DepartmentRequestDTO(
        @NotBlank @NotEmpty String name,
        @NotBlank @NotEmpty String description,
        @NotBlank @NotEmpty String code
) {
}
