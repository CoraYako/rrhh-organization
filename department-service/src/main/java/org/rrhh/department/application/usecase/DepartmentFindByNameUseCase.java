package org.rrhh.department.application.usecase;


import org.rrhh.department.domain.document.Department;

public interface DepartmentFindByNameUseCase {

    Department getDepartmentByName(String name);
}
