package org.rrhh.department.infrastructure.controller.mapper;

import org.rrhh.department.domain.document.Department;
import org.rrhh.department.infrastructure.controller.dto.DepartmentRequestDTO;
import org.rrhh.department.infrastructure.controller.dto.DepartmentResponseDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
public class DepartmentControllerMapperImpl implements GenericMapper<DepartmentResponseDTO, Department, DepartmentRequestDTO> {

    @Override
    public Department toDomain(DepartmentRequestDTO dto) {
        return Department.builder()
                .name(dto.name())
                .description(dto.description())
                .code(dto.code())
                .build();
    }

    @Override
    public DepartmentResponseDTO toDTO(Department document) {
        return DepartmentResponseDTO.builder()
                .id(document.getId().getValue())
                .name(document.getName().getValue())
                .description(document.getDescription().getValue())
                .code(document.getCode().getValue())
                .build();
    }
}
