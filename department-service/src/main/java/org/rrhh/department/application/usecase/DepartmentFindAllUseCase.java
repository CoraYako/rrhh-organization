package org.rrhh.department.application.usecase;

import org.rrhh.department.domain.document.Department;

import java.util.List;

public interface DepartmentFindAllUseCase {

    List<Department> getDepartments();
}
