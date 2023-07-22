package org.rrhh.department.application.implementation;

import org.rrhh.department.application.usecase.DepartmentFindByIdUseCase;
import org.rrhh.department.domain.document.Department;
import org.rrhh.department.domain.exception.NullParameterException;
import org.rrhh.department.domain.exception.ResourceNotFoundException;
import org.rrhh.department.domain.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentFindById implements DepartmentFindByIdUseCase {

    private final DepartmentRepository departmentRepository;

    public DepartmentFindById(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department getDepartmentById(String id) {
        if (id.trim().isEmpty())
            throw new NullParameterException("Department ID");
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        return optionalDepartment
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", id));
    }
}
