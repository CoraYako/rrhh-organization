package org.rrhh.department.infrastructure.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class DepartmentResponseDto {

    private String id;
    private String name;
    private String description;
    private String code;
}
