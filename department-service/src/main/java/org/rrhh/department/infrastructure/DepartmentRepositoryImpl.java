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
    public Optional<Department> findById(String id) {
        Optional<DepartmentDocument> optionalDepartmentDocument = departmentPersistence.findById(id);
        return optionalDepartmentDocument.map(departmentMapper::toDomain);
    }

    @Override
    public void delete(Department department) {
        DepartmentDocument departmentDocument = departmentMapper.toDocumentComplete(department);
        departmentPersistence.delete(departmentDocument);
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
}
