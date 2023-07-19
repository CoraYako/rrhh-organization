package org.rrhh.department.application;

import org.rrhh.department.domain.document.Department;
import org.rrhh.department.domain.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

@Service
public class DepartmentDeleteUseCase {

    private final DepartmentRepository departmentRepository;

    public DepartmentDeleteUseCase(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public void deleteDepartment(Department department) {
        if (department == null)
            throw new RuntimeException("Department to delete must be not null.");

        departmentRepository.delete(department);
    }
}
