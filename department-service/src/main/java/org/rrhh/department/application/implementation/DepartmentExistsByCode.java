package org.rrhh.department.application.implementation;

import org.rrhh.department.application.usecase.DepartmentExistsByCodeUseCase;
import org.rrhh.department.domain.exception.NullParameterException;
import org.rrhh.department.domain.exception.ResourceExistException;
import org.rrhh.department.domain.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

@Service
public class DepartmentExistsByCode implements DepartmentExistsByCodeUseCase {

    private final DepartmentRepository departmentRepository;

    public DepartmentExistsByCode(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public void existsByCode(String departmentCode) {
        if (departmentCode.trim().isEmpty())
            throw new NullParameterException("Code");
        if (departmentRepository.existsByCode(departmentCode))
            throw new ResourceExistException("Department", "code", departmentCode);
    }
}
