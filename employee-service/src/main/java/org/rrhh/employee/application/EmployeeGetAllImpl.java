package org.rrhh.employee.application;

import org.rrhh.employee.domain.document.Employee;
import org.rrhh.employee.domain.repository.EmployeeRepository;
import org.rrhh.employee.domain.usecase.EmployeeGetAllUseCase;
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
        return employeeRepository.findAll();
    }
}
