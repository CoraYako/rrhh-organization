package org.rrhh.employee.infrastructure.repository.mapper;

import org.rrhh.employee.domain.document.Employee;
import org.rrhh.employee.infrastructure.repository.document.EmployeeDocument;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public Employee toDomain(EmployeeDocument employeeDocument) {
        return Employee.builder()
                .id(employeeDocument.getId())
                .firstName(employeeDocument.getFirstName())
                .lastName(employeeDocument.getLastName())
                .email(employeeDocument.getEmail())
                .build();
    }

    @Override
    public EmployeeDocument toDocument(Employee employee) {
        return EmployeeDocument.builder()
                .firstName(employee.getFirstName().getValue())
                .lastName(employee.getLastName().getValue())
                .email(employee.getEmail().getValue())
                .build();
    }

    @Override
    public EmployeeDocument toDocumentComplete(Employee employee) {
        return EmployeeDocument.builder()
                .id(employee.getId().getValue())
                .firstName(employee.getFirstName().getValue())
                .lastName(employee.getLastName().getValue())
                .email(employee.getEmail().getValue())
                .build();
    }
}