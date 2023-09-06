package org.rrhh.department.application;

import org.rrhh.department.domain.document.Department;
import org.rrhh.department.domain.repository.DepartmentRepository;
import org.rrhh.department.domain.usecase.DepartmentGetAllUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentGetAllImpl implements DepartmentGetAllUseCase {

    private final DepartmentRepository departmentRepository;

    public DepartmentGetAllImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<Department> getDepartments() {
        return departmentRepository.findAll();
    }
}
