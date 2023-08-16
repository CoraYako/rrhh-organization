package org.rrhh.department.application.implementation;

import org.rrhh.department.application.usecase.DepartmentGetAllUseCase;
import org.rrhh.department.domain.document.Department;
import org.rrhh.department.domain.exception.EmptyListException;
import org.rrhh.department.domain.repository.DepartmentRepository;
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
        List<Department> departments = departmentRepository.findAll();
        if (departments.isEmpty())
            throw new EmptyListException("No departments found");
        return departments;
    }
}
