package org.rrhh.department.infrastructure.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Schema(description = "DTO model used for list departments with its information")
@Builder
public record DepartmentListDTO(
        @Schema(description = "Department information")
        List<DepartmentResponseDTO> departments
) {
}
