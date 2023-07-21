package org.rrhh.department.application.implementation;

import org.rrhh.department.application.usecase.DepartmentFindAllUseCase;
import org.rrhh.department.domain.document.Department;
import org.rrhh.department.domain.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentFindAll implements DepartmentFindAllUseCase {

    private final DepartmentRepository departmentRepository;

    public DepartmentFindAll(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<Department> getDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return Optional.of(departments).orElseThrow(() -> new RuntimeException("No departments found"));
    }
}
