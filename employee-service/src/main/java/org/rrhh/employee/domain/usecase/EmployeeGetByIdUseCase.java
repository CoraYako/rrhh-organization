package org.rrhh.employee.domain.usecase;

import org.rrhh.employee.domain.document.Employee;

public interface EmployeeGetByIdUseCase {

    Employee getEmployeeById(String id);
}
