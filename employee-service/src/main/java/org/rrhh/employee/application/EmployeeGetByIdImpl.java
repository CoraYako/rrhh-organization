package org.rrhh.employee.application;

import org.rrhh.department.domain.document.Department;
import org.rrhh.department.domain.usecase.DepartmentGetByCodeUseCase;
import org.rrhh.employee.domain.document.Employee;
import org.rrhh.employee.domain.exception.NullParameterException;
import org.rrhh.employee.domain.exception.ResourceNotFoundException;
import org.rrhh.employee.domain.repository.EmployeeRepository;
import org.rrhh.employee.domain.usecase.EmployeeGetByIdUseCase;
import org.rrhh.organization.domain.document.Organization;
import org.rrhh.organization.domain.usecase.OrganizationGetByCodeUseCase;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeGetByIdImpl implements EmployeeGetByIdUseCase {

    private final EmployeeRepository employeeRepository;
    private final DepartmentGetByCodeUseCase departmentFindByCode;
    private final OrganizationGetByCodeUseCase organizationGetByCode;

    public EmployeeGetByIdImpl(EmployeeRepository employeeRepository,
                               DepartmentGetByCodeUseCase departmentFindByCode,
                               OrganizationGetByCodeUseCase organizationGetByCode) {
        this.employeeRepository = employeeRepository;
        this.departmentFindByCode = departmentFindByCode;
        this.organizationGetByCode = organizationGetByCode;
    }

    @Override
    public Employee getEmployeeById(String employeeId) {
        if (employeeId.trim().isEmpty())
            throw new NullParameterException("Employee");

        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        if (optionalEmployee.isEmpty())
            throw new ResourceNotFoundException("Employee", "ID", employeeId);

        Employee employeeFound = optionalEmployee.get();

        String departmentCode = employeeFound.getDepartmentCode().getValue();
        Department department = departmentFindByCode.getDepartmentByCode(departmentCode);

        String organizationCode = employeeFound.getOrganizationCode().getValue();
        Organization organization = organizationGetByCode.getOrganizationByCode(organizationCode);

        employeeFound = Employee.builder()
                .id(employeeFound.getId().getValue())
                .firstName(employeeFound.getFirstName().getValue())
                .lastName(employeeFound.getLastName().getValue())
                .email(employeeFound.getEmail().getValue())
                .departmentCode(departmentCode)
                .organizationCode(organizationCode)
                .organization(organization)
                .department(department)
                .build();

        return employeeFound;
    }
}
