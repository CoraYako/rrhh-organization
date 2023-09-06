package org.rrhh.employee.infrastructure.repository.mapper;

import org.rrhh.employee.domain.document.Employee;
import org.rrhh.employee.infrastructure.repository.document.EmployeeDocument;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public Employee toDomain(EmployeeDocument employeeDocument) {
        return Employee.builder()
                .id(employeeDocument.getId())
                .firstName(employeeDocument.getFirstName())
                .lastName(employeeDocument.getLastName())
                .email(employeeDocument.getEmail())
                .departmentCode(employeeDocument.getDepartmentCode())
                .organizationCode(employeeDocument.getOrganizationCode())
                .build();
    }

    @Override
    public EmployeeDocument toDocument(Employee employee) {
        return EmployeeDocument.builder()
                .firstName(employee.getFirstName().getValue())
                .lastName(employee.getLastName().getValue())
                .email(employee.getEmail().getValue())
                .departmentCode(employee.getDepartmentCode().getValue())
                .organizationCode(employee.getOrganizationCode().getValue())
                .build();
    }

    @Override
    public EmployeeDocument toDocumentComplete(Employee employee) {
        return EmployeeDocument.builder()
                .id(employee.getId().getValue())
                .firstName(employee.getFirstName().getValue())
                .lastName(employee.getLastName().getValue())
                .email(employee.getEmail().getValue())
                .departmentCode(employee.getDepartmentCode().getValue())
                .organizationCode(employee.getOrganizationCode().getValue())
                .build();
    }
}
