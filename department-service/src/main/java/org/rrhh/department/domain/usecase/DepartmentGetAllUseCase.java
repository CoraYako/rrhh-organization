package org.rrhh.department.domain.usecase;

import org.rrhh.department.domain.document.Department;

import java.util.List;

public interface DepartmentGetAllUseCase {

    List<Department> getDepartments();
}
