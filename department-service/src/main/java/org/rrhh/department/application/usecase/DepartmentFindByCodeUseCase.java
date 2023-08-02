package org.rrhh.department.application.usecase;

import org.rrhh.department.domain.document.Department;

public interface DepartmentFindByCodeUseCase {

    Department getDepartmentByCode(String code);
}
