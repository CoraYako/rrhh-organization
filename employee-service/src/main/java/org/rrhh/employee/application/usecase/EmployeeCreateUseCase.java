package org.rrhh.employee.application.usecase;

import org.rrhh.employee.domain.document.Employee;

public interface EmployeeCreateUseCase {

    Employee createEmployee(Employee employee);
}
