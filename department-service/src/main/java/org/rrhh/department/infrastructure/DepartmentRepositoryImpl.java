package org.rrhh.department.infrastructure;

import lombok.RequiredArgsConstructor;
import org.rrhh.department.domain.document.Department;
import org.rrhh.department.domain.repository.DepartmentRepository;
import org.rrhh.department.infrastructure.repository.data.DepartmentPersistence;
import org.rrhh.department.infrastructure.repository.document.DepartmentDocument;
import org.rrhh.department.infrastructure.repository.mapper.DepartmentMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DepartmentRepositoryImpl implements DepartmentRepository {

    private final DepartmentPersistence departmentPersistence;
    private final DepartmentMapper departmentMapper;

    @Override
    public Department save(Department department) {
        DepartmentDocument departmentDocument = departmentMapper.toDocument(department);
        return departmentMapper.toDomain(departmentPersistence.insert(departmentDocument));
    }

    @Override
    public List<Department> findAll() {
        return departmentPersistence.findAll()
                .stream()
                .map(departmentMapper::toDomain)
                .toList();
    }

    @Override
    public boolean existsByName(String name) {
        return departmentPersistence.existsByName(name);
    }

    @Override
    public Optional<Department> findByCode(String code) {
        Optional<DepartmentDocument> optionalDepartmentDocument = departmentPersistence.findByCode(code);
        return optionalDepartmentDocument.map(departmentMapper::toDomain);
    }

    @Override
    public boolean existsByCode(String code) {
        return departmentPersistence.existsByCode(code);
    }
}
