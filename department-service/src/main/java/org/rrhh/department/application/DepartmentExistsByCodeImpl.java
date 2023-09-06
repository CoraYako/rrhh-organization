package org.rrhh.department.application;

import org.rrhh.department.domain.exception.NullParameterException;
import org.rrhh.department.domain.exception.ResourceExistException;
import org.rrhh.department.domain.repository.DepartmentRepository;
import org.rrhh.department.domain.usecase.DepartmentExistsByCodeUseCase;
import org.springframework.stereotype.Service;

@Service
public class DepartmentExistsByCodeImpl implements DepartmentExistsByCodeUseCase {

    private final DepartmentRepository departmentRepository;

    public DepartmentExistsByCodeImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public void existsDepartmentByCode(String departmentCode) {
        if (departmentCode.trim().isEmpty())
            throw new NullParameterException("Department code");
        if (departmentRepository.existsByCode(departmentCode))
            throw new ResourceExistException("Department", "code", departmentCode);
    }
}
