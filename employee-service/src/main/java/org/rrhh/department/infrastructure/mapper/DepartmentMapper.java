package org.rrhh.department.infrastructure.mapper;

import jakarta.validation.constraints.NotNull;
import org.rrhh.department.domain.document.Department;
import org.rrhh.department.infrastructure.dto.DepartmentResponseDTO;

public interface DepartmentMapper {

    Department toDomain(@NotNull DepartmentResponseDTO dto);

    DepartmentResponseDTO toDTO(@NotNull Department department);
}
