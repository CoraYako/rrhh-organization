package org.rrhh.employee.domain.repository;

import org.rrhh.employee.domain.document.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {

    Employee save(Employee employee);

    Optional<Employee> findById(String id);

    List<Employee> findAll();

    void delete(Employee employee);

    boolean existsByEmail(String email);
}
