package org.rrhh.employee.application.implementation;

import org.rrhh.employee.application.usecase.EmployeeSaveUseCase;
import org.rrhh.employee.domain.document.Employee;
import org.rrhh.employee.domain.exception.NullParameterException;
import org.rrhh.employee.domain.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class EmployeeSave implements EmployeeSaveUseCase {

    private final EmployeeRepository employeeRepository;

    public EmployeeSave(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        if (Objects.isNull(employee))
            throw new NullParameterException("Employee");
        return employeeRepository.save(employee);
    }
}
