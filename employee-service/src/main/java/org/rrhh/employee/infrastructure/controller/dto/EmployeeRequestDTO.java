package org.rrhh.employee.infrastructure.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record EmployeeRequestDTO(
        @NotEmpty @NotBlank String firstName,
        @NotEmpty @NotBlank String lastName,
        @NotEmpty @NotBlank @Email String email,
        @NotEmpty @NotBlank String departmentCode,
        @NotEmpty @NotBlank String organizationCode
) {
}
