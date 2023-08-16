package org.rrhh.employee.application.usecase;

import org.rrhh.employee.domain.document.Employee;

public interface EmployeeGetByIdUseCase {

    Employee getEmployeeById(String id);
}
