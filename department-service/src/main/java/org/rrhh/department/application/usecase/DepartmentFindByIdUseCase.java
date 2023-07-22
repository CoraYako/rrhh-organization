package org.rrhh.department.application.usecase;

import org.rrhh.department.domain.document.Department;

public interface DepartmentFindByIdUseCase {

    Department getDepartmentById(String id);
}
