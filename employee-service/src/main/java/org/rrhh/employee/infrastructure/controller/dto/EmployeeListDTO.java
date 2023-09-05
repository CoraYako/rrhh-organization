package org.rrhh.employee.infrastructure.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Schema(description = "DTO model used for list employees with its basic information")
@Builder
public record EmployeeListDTO(
        @Schema(description = "Employee information")
        List<EmployeeBasicResponseDTO> employees
) {
}
