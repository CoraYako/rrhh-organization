package org.rrhh.department.application;

import org.rrhh.department.domain.document.Department;
import org.rrhh.department.domain.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

@Service
public class DepartmentSaveUseCase {

    private final DepartmentRepository departmentRepository;

    public DepartmentSaveUseCase(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Department save(Department department) {
        if (department == null)
            throw new RuntimeException("Department to save must be not null.");

        return departmentRepository.save(department);
    }
}
