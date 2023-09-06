package org.rrhh.department.application;

import org.rrhh.department.domain.document.Department;
import org.rrhh.department.domain.exception.NullParameterException;
import org.rrhh.department.domain.repository.DepartmentRepository;
import org.rrhh.department.domain.usecase.DepartmentCreateUseCase;
import org.rrhh.department.domain.usecase.DepartmentExistsByCodeUseCase;
import org.rrhh.department.domain.usecase.DepartmentExistsByNameUseCase;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class DepartmentCreateImpl implements DepartmentCreateUseCase {

    private final DepartmentRepository departmentRepository;
    private final DepartmentExistsByNameUseCase departmentExistsByName;
    private final DepartmentExistsByCodeUseCase departmentExistsByCode;

    public DepartmentCreateImpl(DepartmentRepository departmentRepository,
                                DepartmentExistsByNameUseCase departmentExistByName,
                                DepartmentExistsByCodeUseCase departmentExistsByCode) {
        this.departmentRepository = departmentRepository;
        this.departmentExistsByName = departmentExistByName;
        this.departmentExistsByCode = departmentExistsByCode;
    }

    @Override
    public Department createDepartment(Department department) {
        if (Objects.isNull(department))
            throw new NullParameterException("Department");

        String departmentName = department.getName().getValue();
        String departmentCode = department.getCode().getValue();
        departmentExistsByName.existsDepartmentByName(departmentName);
        departmentExistsByCode.existsDepartmentByCode(departmentCode);

        return departmentRepository.save(department);
    }
}
