package org.rrhh.employee.application;

import org.rrhh.department.domain.document.Department;
import org.rrhh.department.domain.usecase.DepartmentGetByCodeUseCase;
import org.rrhh.employee.domain.document.Employee;
import org.rrhh.employee.domain.exception.NullParameterException;
import org.rrhh.employee.domain.repository.EmployeeRepository;
import org.rrhh.employee.domain.usecase.EmployeeCreateUseCase;
import org.rrhh.employee.domain.usecase.EmployeeExistsByEmailUseCase;
import org.rrhh.organization.domain.document.Organization;
import org.rrhh.organization.domain.usecase.OrganizationGetByCodeUseCase;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class EmployeeCreateImpl implements EmployeeCreateUseCase {

    private final EmployeeRepository employeeRepository;
    private final EmployeeExistsByEmailUseCase employeeExistsByEmail;

    private final DepartmentGetByCodeUseCase departmentGetByCode;
    private final OrganizationGetByCodeUseCase organizationGetByCode;

    public EmployeeCreateImpl(EmployeeRepository employeeRepository,
                              EmployeeExistsByEmailUseCase employeeExistsByEmail,
                              DepartmentGetByCodeUseCase departmentGetByCode,
                              OrganizationGetByCodeUseCase organizationGetByCode) {
        this.employeeRepository = employeeRepository;
        this.employeeExistsByEmail = employeeExistsByEmail;
        this.departmentGetByCode = departmentGetByCode;
        this.organizationGetByCode = organizationGetByCode;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        if (Objects.isNull(employee))
            throw new NullParameterException("Employee");

        String employeeEmail = employee.getEmail().getValue();
        employeeExistsByEmail.existsEmployeeByEmail(employeeEmail);

        String departmentCode = employee.getDepartmentCode().getValue();
        Department department = departmentGetByCode.getDepartmentByCode(departmentCode);

        String organizationCode = employee.getOrganizationCode().getValue();
        Organization organization = organizationGetByCode.getOrganizationByCode(organizationCode);

        Employee employeeSaved = employeeRepository.save(employee);
        employee = Employee.builder()
                .id(employeeSaved.getId().getValue())
                .firstName(employeeSaved.getFirstName().getValue())
                .lastName(employeeSaved.getLastName().getValue())
                .email(employeeSaved.getEmail().getValue())
                .departmentCode(departmentCode)
                .organizationCode(organizationCode)
                .department(department)
                .organization(organization)
                .build();

        return employee;
    }
}
