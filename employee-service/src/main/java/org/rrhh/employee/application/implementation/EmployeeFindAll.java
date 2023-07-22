package org.rrhh.employee.application.implementation;

import org.rrhh.employee.application.usecase.EmployeeFindAllUseCase;
import org.rrhh.employee.domain.document.Employee;
import org.rrhh.employee.domain.exception.EmptyListException;
import org.rrhh.employee.domain.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeFindAll implements EmployeeFindAllUseCase {

    private final EmployeeRepository employeeRepository;

    public EmployeeFindAll(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return Optional.of(employees).orElseThrow(() -> new EmptyListException("No Employees found"));
    }
}
