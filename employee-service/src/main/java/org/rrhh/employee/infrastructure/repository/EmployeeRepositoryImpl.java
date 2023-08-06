package org.rrhh.employee.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import org.rrhh.employee.domain.document.Employee;
import org.rrhh.employee.domain.repository.EmployeeRepository;
import org.rrhh.employee.infrastructure.repository.data.EmployeePersistence;
import org.rrhh.employee.infrastructure.repository.document.EmployeeDocument;
import org.rrhh.employee.infrastructure.repository.mapper.EmployeeMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final EmployeePersistence employeePersistence;
    private final EmployeeMapper employeeMapper;

    @Override
    public Employee save(Employee employee) {
        EmployeeDocument employeeDocument = employeeMapper.toDocument(employee);
        employeeDocument = employeePersistence.save(employeeDocument);
        return employeeMapper.toDomain(employeeDocument);
    }

    @Override
    public Optional<Employee> findById(String id) {
        Optional<EmployeeDocument> optionalEmployeeDocument = employeePersistence.findById(id);
        return optionalEmployeeDocument.map(employeeMapper::toDomain);
    }

    @Override
    public List<Employee> findAll() {
        return employeePersistence.findAll().stream()
                .map(employeeMapper::toDomain)
                .toList();
    }

    @Override
    public void delete(Employee employee) {
        EmployeeDocument employeeDocument = employeeMapper.toDocumentComplete(employee);
        employeePersistence.delete(employeeDocument);
    }

    @Override
    public boolean existsByEmail(String email) {
        return employeePersistence.existsByEmail(email);
    }
}
