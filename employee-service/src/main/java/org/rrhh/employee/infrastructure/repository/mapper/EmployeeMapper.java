package org.rrhh.employee.infrastructure.repository.mapper;

import jakarta.validation.constraints.NotNull;
import org.rrhh.employee.domain.document.Employee;
import org.rrhh.employee.infrastructure.repository.document.EmployeeDocument;

public interface EmployeeMapper {

    Employee toDomain(@NotNull EmployeeDocument employeeDocument);

    EmployeeDocument toDocument(@NotNull Employee employee);

    EmployeeDocument toDocumentComplete(@NotNull Employee employee);
}
