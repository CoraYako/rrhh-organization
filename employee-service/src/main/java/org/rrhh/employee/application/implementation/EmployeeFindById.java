package org.rrhh.employee.application.implementation;

import org.rrhh.employee.application.usecase.EmployeeFindByIdUseCase;
import org.rrhh.employee.domain.document.Employee;
import org.rrhh.employee.domain.exception.NullParameterException;
import org.rrhh.employee.domain.exception.ResourceNotFoundException;
import org.rrhh.employee.domain.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeFindById implements EmployeeFindByIdUseCase {

    private final EmployeeRepository employeeRepository;

    public EmployeeFindById(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee getEmployeeById(String employeeId) {
        if (employeeId.trim().isEmpty())
            throw new NullParameterException("Employee");

        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        return optionalEmployee
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "ID", employeeId));
    }
}
