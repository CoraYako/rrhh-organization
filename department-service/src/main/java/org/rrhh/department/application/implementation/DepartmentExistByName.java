package org.rrhh.department.application.implementation;

import org.rrhh.department.application.usecase.DepartmentExistByNameUseCase;
import org.rrhh.department.domain.exception.NullParameterException;
import org.rrhh.department.domain.exception.ResourceExistException;
import org.rrhh.department.domain.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

@Service
public class DepartmentExistByName implements DepartmentExistByNameUseCase {

    private final DepartmentRepository departmentRepository;

    public DepartmentExistByName(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public void existsByName(String name) {
        if (name.trim().isEmpty())
            throw new NullParameterException("Name");
        if (departmentRepository.existsByName(name))
            throw new ResourceExistException("Department", "name", name);
    }
}
