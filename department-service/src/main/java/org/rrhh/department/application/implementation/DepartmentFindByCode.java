package org.rrhh.department.application.implementation;

import org.rrhh.department.application.usecase.DepartmentFindByCodeUseCase;
import org.rrhh.department.domain.document.Department;
import org.rrhh.department.domain.exception.NullParameterException;
import org.rrhh.department.domain.exception.ResourceNotFoundException;
import org.rrhh.department.domain.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentFindByCode implements DepartmentFindByCodeUseCase {

    private final DepartmentRepository departmentRepository;

    public DepartmentFindByCode(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department getDepartmentByCode(String code) {
        if (code.trim().isEmpty())
            throw new NullParameterException("Code");
        Optional<Department> optionalDepartment = departmentRepository.findByCode(code);
        return optionalDepartment
                .orElseThrow(() -> new ResourceNotFoundException("Department", "code", code));
    }
}
