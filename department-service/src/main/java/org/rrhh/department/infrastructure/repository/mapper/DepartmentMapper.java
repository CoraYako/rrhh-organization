package org.rrhh.department.infrastructure.repository.mapper;

import jakarta.validation.constraints.NotNull;
import org.rrhh.department.domain.document.Department;
import org.rrhh.department.infrastructure.repository.document.DepartmentDocument;

public interface DepartmentMapper {

    DepartmentDocument toDocument(@NotNull Department department);

    DepartmentDocument toDocumentComplete(@NotNull Department department);

    Department toDomain(@NotNull DepartmentDocument departmentDocument);
}
