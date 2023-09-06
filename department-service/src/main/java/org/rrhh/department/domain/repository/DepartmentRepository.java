package org.rrhh.department.domain.repository;

import org.rrhh.department.domain.document.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository {

    Department save(Department department);

    List<Department> findAll();

    boolean existsByName(String name);

    Optional<Department> findByCode(String code);

    boolean existsByCode(String code);
}
