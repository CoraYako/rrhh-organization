package org.rrhh.department.application.implementation;

import org.rrhh.department.application.usecase.DepartmentExistByNameUseCase;
import org.rrhh.department.application.usecase.DepartmentSaveUseCase;
import org.rrhh.department.domain.document.Department;
import org.rrhh.department.domain.exception.NullParameterException;
import org.rrhh.department.domain.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class DepartmentSave implements DepartmentSaveUseCase {

    private final DepartmentRepository departmentRepository;
    private final DepartmentExistByNameUseCase departmentExistByName;

    public DepartmentSave(DepartmentRepository departmentRepository, DepartmentExistByNameUseCase departmentExistByName) {
        this.departmentRepository = departmentRepository;
        this.departmentExistByName = departmentExistByName;
    }

    @Override
    public Department save(Department department) {
        if (Objects.isNull(department))
            throw new NullParameterException("Department");

        String departmentName = department.getName().getValue();
        departmentExistByName.existsByName(departmentName);

        return departmentRepository.save(department);
    }
}
