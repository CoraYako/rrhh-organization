package org.rrhh.employee.application.implementation;

import org.rrhh.department.application.usecase.DepartmentFindByCodeUseCase;
import org.rrhh.department.domain.document.Department;
import org.rrhh.employee.application.usecase.EmployeeExistsByEmailUseCase;
import org.rrhh.employee.application.usecase.EmployeeSaveUseCase;
import org.rrhh.employee.domain.document.Employee;
import org.rrhh.employee.domain.exception.NullParameterException;
import org.rrhh.employee.domain.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class EmployeeSave implements EmployeeSaveUseCase {

    private final EmployeeRepository employeeRepository;
    private final EmployeeExistsByEmailUseCase employeeExistsByEmail;

    private final DepartmentFindByCodeUseCase departmentFindByCodeUseCase;

    public EmployeeSave(EmployeeRepository employeeRepository,
                        EmployeeExistsByEmailUseCase employeeExistsByEmail,
                        DepartmentFindByCodeUseCase departmentFindByCodeUseCase) {
        this.employeeRepository = employeeRepository;
        this.employeeExistsByEmail = employeeExistsByEmail;
        this.departmentFindByCodeUseCase = departmentFindByCodeUseCase;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        if (Objects.isNull(employee))
            throw new NullParameterException("Employee");

        String employeeEmail = employee.getEmail().getValue();
        employeeExistsByEmail.existsByEmail(employeeEmail);

        String departmentCode = employee.getDepartmentCode().getValue();
        Department department = departmentFindByCodeUseCase.getDepartmentByCode(departmentCode);

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
