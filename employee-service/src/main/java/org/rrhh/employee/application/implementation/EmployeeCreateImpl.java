package org.rrhh.employee.application.implementation;

import org.rrhh.department.application.usecase.DepartmentGetByCodeUseCase;
import org.rrhh.department.domain.document.Department;
import org.rrhh.employee.application.usecase.EmployeeCreateUseCase;
import org.rrhh.employee.application.usecase.EmployeeExistsByEmailUseCase;
import org.rrhh.employee.domain.document.Employee;
import org.rrhh.employee.domain.exception.NullParameterException;
import org.rrhh.employee.domain.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class EmployeeCreateImpl implements EmployeeCreateUseCase {

    private final EmployeeRepository employeeRepository;
    private final EmployeeExistsByEmailUseCase employeeExistsByEmail;

    private final DepartmentGetByCodeUseCase departmentFindByCode;

    public EmployeeCreateImpl(EmployeeRepository employeeRepository,
                              EmployeeExistsByEmailUseCase employeeExistsByEmail,
                              DepartmentGetByCodeUseCase departmentFindByCode) {
        this.employeeRepository = employeeRepository;
        this.employeeExistsByEmail = employeeExistsByEmail;
        this.departmentFindByCode = departmentFindByCode;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        if (Objects.isNull(employee))
            throw new NullParameterException("Employee");

        String employeeEmail = employee.getEmail().getValue();
        employeeExistsByEmail.existsEmployeeByEmail(employeeEmail);

        String departmentCode = employee.getDepartmentCode().getValue();
        Department department = departmentFindByCode.getDepartmentByCode(departmentCode);

        Employee employeeSaved = employeeRepository.save(employee);
        employee = Employee.builder()
                .id(employeeSaved.getId().getValue())
                .firstName(employeeSaved.getFirstName().getValue())
                .lastName(employeeSaved.getLastName().getValue())
                .email(employeeSaved.getEmail().getValue())
                .departmentCode(employeeSaved.getDepartmentCode().getValue())
                .department(department)
                .build();

        return employee;
    }
}
