package org.rrhh.employee.application.implementation;

import org.rrhh.department.application.usecase.DepartmentGetByCodeUseCase;
import org.rrhh.department.domain.document.Department;
import org.rrhh.employee.application.usecase.EmployeeGetByIdUseCase;
import org.rrhh.employee.domain.document.Employee;
import org.rrhh.employee.domain.exception.NullParameterException;
import org.rrhh.employee.domain.exception.ResourceNotFoundException;
import org.rrhh.employee.domain.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeGetByIdImpl implements EmployeeGetByIdUseCase {

    private final EmployeeRepository employeeRepository;

    private final DepartmentGetByCodeUseCase departmentFindByCode;

    public EmployeeGetByIdImpl(EmployeeRepository employeeRepository, DepartmentGetByCodeUseCase departmentFindByCode) {
        this.employeeRepository = employeeRepository;
        this.departmentFindByCode = departmentFindByCode;
    }

    @Override
    public Employee getEmployeeById(String employeeId) {
        if (employeeId.trim().isEmpty())
            throw new NullParameterException("Employee");

        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        if (optionalEmployee.isEmpty())
            throw new ResourceNotFoundException("Employee", "ID", employeeId);

        String departmentCode = optionalEmployee.get().getDepartmentCode().getValue();
        Department department = departmentFindByCode.getDepartmentByCode(departmentCode);
        Employee employeeFound = optionalEmployee.get();
        employeeFound = Employee.builder()
                .id(employeeFound.getId().getValue())
                .firstName(employeeFound.getFirstName().getValue())
                .lastName(employeeFound.getLastName().getValue())
                .email(employeeFound.getEmail().getValue())
                .departmentCode(employeeFound.getDepartmentCode().getValue())
                .department(department)
                .build();

        return employeeFound;
    }
}