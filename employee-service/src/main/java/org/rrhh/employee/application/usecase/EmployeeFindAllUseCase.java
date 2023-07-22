package org.rrhh.employee.application.usecase;

import org.rrhh.employee.domain.document.Employee;

import java.util.List;

public interface EmployeeFindAllUseCase {

    List<Employee> getEmployees();
}