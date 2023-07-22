package org.rrhh.employee.infrastructure.repository.mapper;

import org.rrhh.employee.domain.document.Employee;
import org.rrhh.employee.infrastructure.repository.document.EmployeeDocument;

public interface EmployeeMapper {

    Employee toDomain(EmployeeDocument employeeDocument);

    EmployeeDocument toDocument(Employee employee);

    EmployeeDocument toDocumentComplete(Employee employee);
}
