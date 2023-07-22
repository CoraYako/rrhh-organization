package org.rrhh.employee.application.usecase;

import org.rrhh.employee.domain.document.Employee;

public interface EmployeeSaveUseCase {

    Employee createEmployee(Employee employee);
}
