package org.rrhh.department.infrastructure.mapper;

import org.rrhh.department.domain.document.Department;
import org.rrhh.department.infrastructure.dto.DepartmentResponseDTO;

public interface DepartmentMapper {

    Department toDomain(DepartmentResponseDTO dto);

    DepartmentResponseDTO toDTO(Department department);
}
