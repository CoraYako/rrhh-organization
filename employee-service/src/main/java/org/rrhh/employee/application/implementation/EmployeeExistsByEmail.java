package org.rrhh.employee.application.implementation;

import org.rrhh.employee.application.usecase.EmployeeExistsByEmailUseCase;
import org.rrhh.employee.domain.exception.NullParameterException;
import org.rrhh.employee.domain.exception.ResourceExistException;
import org.rrhh.employee.domain.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeExistsByEmail implements EmployeeExistsByEmailUseCase {

    private final EmployeeRepository employeeRepository;

    public EmployeeExistsByEmail(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // TODO: 22/7/2023 use custom exceptions
    @Override
    public void existsByEmail(String email) {
        if (email.trim().isEmpty())
            throw new NullParameterException("Email");
       if (employeeRepository.existsByEmail(email))
           throw new ResourceExistException("Employee", "email", email);
    }
}
