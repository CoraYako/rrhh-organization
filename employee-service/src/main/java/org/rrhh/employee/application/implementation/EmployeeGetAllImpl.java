package org.rrhh.employee.application.implementation;

import org.rrhh.employee.application.usecase.EmployeeGetAllUseCase;
import org.rrhh.employee.domain.document.Employee;
import org.rrhh.employee.domain.exception.EmptyListException;
import org.rrhh.employee.domain.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeGetAllImpl implements EmployeeGetAllUseCase {

    private final EmployeeRepository employeeRepository;

    public EmployeeGetAllImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        if (employees.isEmpty())
            throw new EmptyListException("No Employees found");
        return employees;
    }
}
