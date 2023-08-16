package org.rrhh.employee.application.implementation;

import org.rrhh.employee.application.usecase.EmployeeDeleteUseCase;
import org.rrhh.employee.domain.document.Employee;
import org.rrhh.employee.domain.exception.NullParameterException;
import org.rrhh.employee.domain.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class EmployeeDeleteImpl implements EmployeeDeleteUseCase {

    private final EmployeeRepository employeeRepository;

    public EmployeeDeleteImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void deleteEmployee(Employee employee) {
        if (Objects.isNull(employee))
            throw new NullParameterException("Employee");
        employeeRepository.delete(employee);
    }
}
