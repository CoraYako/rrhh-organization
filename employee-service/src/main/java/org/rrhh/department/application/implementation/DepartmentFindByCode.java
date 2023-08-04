package org.rrhh.department.application.implementation;

import org.rrhh.department.application.usecase.DepartmentFindByCodeUseCase;
import org.rrhh.department.domain.document.Department;
import org.rrhh.department.domain.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

@Service
public class DepartmentFindByCode implements DepartmentFindByCodeUseCase {

    private final DepartmentRepository departmentRepository;

    public DepartmentFindByCode(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department getDepartmentByCode(String code) {
        return departmentRepository.findByCode(code);
    }
}
