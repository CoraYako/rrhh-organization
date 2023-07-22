package org.rrhh.employee.application.usecase;

import org.rrhh.employee.domain.document.Employee;

public interface EmployeeFindByIdUseCase {

    Employee getEmployeeById(String id);
}
