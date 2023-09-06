package org.rrhh.department.infrastructure.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Schema(description = "DTO model used as request to register a department, all the information is required")
@Builder
public record DepartmentRequestDTO(
        @Schema(description = "Department Name", example = "Information Technology Department")
        @NotBlank @NotEmpty String name,
        @Schema(description = "A brief description of the current Department",
                example = "It is responsible for the management of information " +
                "that the business, related to the Internet, computing and technology.")
        @NotBlank @NotEmpty String description,
        @Schema(description = "Unique Department Code", example = "IT001")
        @NotBlank @NotEmpty String code
) {
}
