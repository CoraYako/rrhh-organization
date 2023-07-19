package org.rrhh.department.application;

import org.rrhh.department.domain.document.Department;
import org.rrhh.department.domain.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentFindByIdUseCase {

    private final DepartmentRepository departmentRepository;

    public DepartmentFindByIdUseCase(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Department getDepartmentById(String id) {
        if (id.trim().isEmpty())
            throw new RuntimeException("The department ID must not be null or empty.");
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        return optionalDepartment.orElseThrow(() -> new RuntimeException("No such department for id " + id));
    }
}
