package org.rrhh.department.infrastructure.mapper;

import org.rrhh.department.domain.document.Department;
import org.rrhh.department.infrastructure.dto.DepartmentResponseDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
public class DepartmentMapperImpl implements DepartmentMapper {

    @Override
    public Department toDomain(DepartmentResponseDTO dto) {
        return Department.builder()
                .id(dto.id())
                .name(dto.name())
                .description(dto.description())
                .code(dto.code())
                .build();
    }

    @Override
    public DepartmentResponseDTO toDTO(Department department) {
        return DepartmentResponseDTO.builder()
                .id(department.getId().getValue())
                .name(department.getName().getValue())
                .description(department.getDescription().getValue())
                .code(department.getCode().getValue())
                .build();
    }
}
