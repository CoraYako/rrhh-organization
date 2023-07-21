package org.rrhh.department.application.implementation;

import org.rrhh.department.application.usecase.DepartmentSaveUseCase;
import org.rrhh.department.domain.document.Department;
import org.rrhh.department.domain.exception.NullParameterException;
import org.rrhh.department.domain.exception.ResourceExistException;
import org.rrhh.department.domain.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class DepartmentSave implements DepartmentSaveUseCase {

    private final DepartmentRepository departmentRepository;

    public DepartmentSave(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department save(Department department) {
        if (Objects.isNull(department))
            throw new NullParameterException("Department");

        String name = department.getName().getValue();
        if (departmentRepository.existsByName(name))
            throw new ResourceExistException("Department", "name", name);

        return departmentRepository.save(department);
    }
}
