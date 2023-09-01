package org.rrhh.employee.infrastructure.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Schema(description = "DTO model used as request to register an employee, all the information is required")
@Builder
public record EmployeeRequestDTO(
        @Schema(description = "First Name", example = "Francisco")
        @NotEmpty @NotBlank String firstName,
        @Schema(description = "Last Name", example = "Orieta")
        @NotEmpty @NotBlank String lastName,
        @Schema(description = "Employee Email", example = "francisco@email.com")
        @NotEmpty @NotBlank @Email String email,
        @Schema(description = "Associated Department Code", example = "IT001")
        @NotEmpty @NotBlank String departmentCode,
        @Schema(description = "Associated Organization Code", example = "SF001")
        @NotEmpty @NotBlank String organizationCode
) {
}
