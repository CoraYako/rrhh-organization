package org.rrhh.department.infrastructure.controller.mapper;

import org.rrhh.department.domain.document.Department;
import org.rrhh.department.infrastructure.controller.dto.DepartmentRequestDto;
import org.rrhh.department.infrastructure.controller.dto.DepartmentResponseDto;
import org.springframework.stereotype.Component;

@Component
public class DocumentMapper implements IGenericMapper<DepartmentResponseDto, Department, DepartmentRequestDto>{

    @Override
    public Department toDomain(DepartmentRequestDto dto) {
        return Department.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .code(dto.getCode())
                .build();
    }

    @Override
    public DepartmentResponseDto toDto(Department document) {
        return DepartmentResponseDto.builder()
                .id(document.getId().getValue())
                .name(document.getName().getValue())
                .description(document.getDescription().getValue())
                .code(document.getCode().getValue())
                .build();
    }
}
