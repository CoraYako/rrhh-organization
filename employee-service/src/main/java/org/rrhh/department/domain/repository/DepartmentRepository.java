package org.rrhh.department.domain.repository;

import org.rrhh.department.domain.document.Department;

public interface DepartmentRepository {

    Department findByCode(String code);
}
