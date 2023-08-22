package org.rrhh.department.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import org.rrhh.department.domain.document.Department;
import org.rrhh.department.domain.repository.DepartmentRepository;
import org.rrhh.department.infrastructure.dto.DepartmentResponseDTO;
import org.rrhh.department.infrastructure.mapper.DepartmentMapper;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DepartmentRepositoryImpl implements DepartmentRepository {

    private final DepartmentAPIClient departmentAPIClient;
    private final DepartmentMapper departmentMapper;

    @Override
    public Department findByCode(String code) {
        DepartmentResponseDTO departmentResponseDTO = departmentAPIClient.getDepartmentByCode(code);
        return departmentMapper.toDomain(departmentResponseDTO);
    }
}
