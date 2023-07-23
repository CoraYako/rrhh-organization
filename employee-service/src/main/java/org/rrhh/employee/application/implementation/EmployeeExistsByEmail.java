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

    @Override
    public void existsByEmail(String employeeEmail) {
        if (employeeEmail.trim().isEmpty())
            throw new NullParameterException("Email");
       if (employeeRepository.existsByEmail(employeeEmail))
           throw new ResourceExistException("Employee", "email", employeeEmail);
    }
}
