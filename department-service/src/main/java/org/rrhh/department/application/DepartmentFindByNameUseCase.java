package org.rrhh.department.application;


import org.rrhh.department.domain.document.Department;
import org.rrhh.department.domain.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentFindByNameUseCase {

    private final DepartmentRepository departmentRepository;

    public DepartmentFindByNameUseCase(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Department getDepartmentByName(String name) {
        if (name.trim().isEmpty())
            throw new RuntimeException("Department name must be not null or empty.");
        Optional<Department> optionalDepartment = departmentRepository.findByName(name);
        return optionalDepartment.orElseThrow(() -> new RuntimeException("No department found for name " + name));
    }
}
