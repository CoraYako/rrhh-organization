package org.rrhh.department.infrastructure.repository.mapper;

import org.rrhh.department.domain.document.Department;
import org.rrhh.department.infrastructure.repository.document.DepartmentDocument;

public interface IDepartmentMapper {

    DepartmentDocument toDocument(Department department);

    DepartmentDocument toDocumentComplete(Department department);

    Department toDomain(DepartmentDocument departmentDocument);
}
