package org.rrhh.department.application;

import org.rrhh.department.domain.exception.NullParameterException;
import org.rrhh.department.domain.exception.ResourceExistException;
import org.rrhh.department.domain.repository.DepartmentRepository;
import org.rrhh.department.domain.usecase.DepartmentExistsByNameUseCase;
import org.springframework.stereotype.Service;

@Service
public class DepartmentExistsByNameImpl implements DepartmentExistsByNameUseCase {

    private final DepartmentRepository departmentRepository;

    public DepartmentExistsByNameImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public void existsDepartmentByName(String departmentName) {
        if (departmentName.trim().isEmpty())
            throw new NullParameterException("Department name");
        if (departmentRepository.existsByName(departmentName))
            throw new ResourceExistException("Department", "name", departmentName);
    }
}
