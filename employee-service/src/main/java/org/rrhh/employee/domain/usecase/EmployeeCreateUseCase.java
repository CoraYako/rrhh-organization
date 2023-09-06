package org.rrhh.employee.domain.usecase;

import org.rrhh.employee.domain.document.Employee;

public interface EmployeeCreateUseCase {

    Employee createEmployee(Employee employee);
}
