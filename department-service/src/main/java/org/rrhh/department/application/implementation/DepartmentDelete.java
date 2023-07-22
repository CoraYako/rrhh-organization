package org.rrhh.department.application.implementation;

import org.rrhh.department.application.usecase.DepartmentDeleteUseCase;
import org.rrhh.department.domain.document.Department;
import org.rrhh.department.domain.exception.NullParameterException;
import org.rrhh.department.domain.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class DepartmentDelete implements DepartmentDeleteUseCase {

    private final DepartmentRepository departmentRepository;

    public DepartmentDelete(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public void deleteDepartment(Department department) {
        if (Objects.isNull(department))
            throw new NullParameterException("Department");

        departmentRepository.delete(department);
    }
}
