package org.rrhh.employee.domain.usecase;

import org.rrhh.employee.domain.document.Employee;

import java.util.List;

public interface EmployeeGetAllUseCase {

    List<Employee> getEmployees();
}
