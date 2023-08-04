package org.rrhh.department.application.implementation;

import org.rrhh.department.application.usecase.DepartmentExistsByCodeUseCase;
import org.rrhh.department.application.usecase.DepartmentExistsByNameUseCase;
import org.rrhh.department.application.usecase.DepartmentSaveUseCase;
import org.rrhh.department.domain.document.Department;
import org.rrhh.department.domain.exception.NullParameterException;
import org.rrhh.department.domain.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class DepartmentSave implements DepartmentSaveUseCase {

    private final DepartmentRepository departmentRepository;
    private final DepartmentExistsByNameUseCase departmentExistsByName;
    private final DepartmentExistsByCodeUseCase departmentExistsByCode;

    public DepartmentSave(DepartmentRepository departmentRepository,
                          DepartmentExistsByNameUseCase departmentExistByName,
                          DepartmentExistsByCodeUseCase departmentExistsByCode) {
        this.departmentRepository = departmentRepository;
        this.departmentExistsByName = departmentExistByName;
        this.departmentExistsByCode = departmentExistsByCode;
    }

    @Override
    public Department save(Department department) {
        if (Objects.isNull(department))
            throw new NullParameterException("Department");

        String departmentName = department.getName().getValue();
        String departmentCode = department.getCode().getValue();
        departmentExistsByName.existsByName(departmentName);
        departmentExistsByCode.existsByCode(departmentCode);

        return departmentRepository.save(department);
    }
}
