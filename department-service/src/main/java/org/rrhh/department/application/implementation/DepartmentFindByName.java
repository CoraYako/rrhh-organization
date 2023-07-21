package org.rrhh.department.application.implementation;


import org.rrhh.department.application.usecase.DepartmentFindByNameUseCase;
import org.rrhh.department.domain.document.Department;
import org.rrhh.department.domain.exception.ResourceNotFoundException;
import org.rrhh.department.domain.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentFindByName implements DepartmentFindByNameUseCase {

    private final DepartmentRepository departmentRepository;

    public DepartmentFindByName(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department getDepartmentByName(String name) {
        if (name.trim().isEmpty())
            throw new RuntimeException("Department name must be not null or empty.");
        Optional<Department> optionalDepartment = departmentRepository.findByName(name);
        return optionalDepartment
                .orElseThrow(() -> new ResourceNotFoundException("Department", "name", name));
    }
}
