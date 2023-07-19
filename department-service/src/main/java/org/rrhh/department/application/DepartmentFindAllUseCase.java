package org.rrhh.department.application;

import org.rrhh.department.domain.document.Department;
import org.rrhh.department.domain.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentFindAllUseCase {

    private final DepartmentRepository departmentRepository;

    public DepartmentFindAllUseCase(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> getDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return Optional.of(departments).orElseThrow(() -> new RuntimeException("No departments found"));
    }
}
