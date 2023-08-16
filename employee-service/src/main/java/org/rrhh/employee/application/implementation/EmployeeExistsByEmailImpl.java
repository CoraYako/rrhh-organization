package org.rrhh.employee.application.implementation;

import org.rrhh.employee.application.usecase.EmployeeExistsByEmailUseCase;
import org.rrhh.employee.domain.exception.NullParameterException;
import org.rrhh.employee.domain.exception.ResourceExistException;
import org.rrhh.employee.domain.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeExistsByEmailImpl implements EmployeeExistsByEmailUseCase {

    private final EmployeeRepository employeeRepository;

    public EmployeeExistsByEmailImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void existsEmployeeByEmail(String employeeEmail) {
        if (employeeEmail.trim().isEmpty())
            throw new NullParameterException("Employee email");
       if (employeeRepository.existsByEmail(employeeEmail))
           throw new ResourceExistException("Employee", "email", employeeEmail);
    }
}
