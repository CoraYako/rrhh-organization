package org.rrhh.department.application;

import org.rrhh.department.domain.document.Department;
import org.rrhh.department.domain.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentFindByCodeUseCase {

    private final DepartmentRepository departmentRepository;

    public DepartmentFindByCodeUseCase(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Department getDepartmentByCode(String code) {
        if (code.trim().isEmpty())
            throw new RuntimeException("Department code must be not null or empty.");
        Optional<Department> optionalDepartment = departmentRepository.findByCode(code);
        return optionalDepartment.orElseThrow(() -> new RuntimeException("No department found for code " + code));
    }
}
