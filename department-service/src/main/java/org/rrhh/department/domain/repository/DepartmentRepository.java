package org.rrhh.department.domain.repository;

import org.rrhh.department.domain.document.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository {

    Department save(Department department);

    Optional<Department> findById(String id);

    void delete(Department department);

    List<Department> findAll();

    Optional<Department> findByName(String name);

    Optional<Department> findByCode(String code);

    boolean existsByName(String name);
}
