package org.rrhh.employee.domain.repository;

import org.rrhh.employee.domain.document.Employee;

import java.util.List;

public interface EmployeeRepository {

    Employee save(Employee employee);

    Employee findById(String id);

    List<Employee> findAll();

    void delete(Employee employee);

    boolean existsByEmail(String email);
}
