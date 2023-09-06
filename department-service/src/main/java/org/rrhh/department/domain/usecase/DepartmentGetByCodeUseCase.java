package org.rrhh.department.domain.usecase;

import org.rrhh.department.domain.document.Department;

public interface DepartmentGetByCodeUseCase {

    Department getDepartmentByCode(String code);
}
