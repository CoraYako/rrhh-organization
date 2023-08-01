package org.rrhh.department.infrastructure.repository.mapper;

import org.rrhh.department.domain.document.Department;
import org.rrhh.department.infrastructure.repository.document.DepartmentDocument;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
public class DepartmentMapperImpl implements DepartmentMapper {

    @Override
    public DepartmentDocument toDocument(Department department) {
        return DepartmentDocument.builder()
                .name(department.getName().getValue())
                .description(department.getDescription().getValue())
                .code(department.getCode().getValue())
                .build();
    }

    @Override
    public DepartmentDocument toDocumentComplete(Department department) {
        return DepartmentDocument.builder()
                .id(department.getId().getValue())
                .name(department.getName().getValue())
                .description(department.getDescription().getValue())
                .code(department.getCode().getValue())
                .build();
    }

    @Override
    public Department toDomain(DepartmentDocument departmentDocument) {
        return Department.builder()
                .id(departmentDocument.getId())
                .name(departmentDocument.getName())
                .description(departmentDocument.getDescription())
                .code(departmentDocument.getCode())
                .build();
    }
}
