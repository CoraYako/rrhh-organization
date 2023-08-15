package org.rrhh.department.application.implementation;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.rrhh.department.application.usecase.DepartmentFindByCodeUseCase;
import org.rrhh.department.domain.document.Department;
import org.rrhh.department.domain.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

@Service
public class DepartmentFindByCode implements DepartmentFindByCodeUseCase {

    private final DepartmentRepository departmentRepository;

    public DepartmentFindByCode(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    @CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    public Department getDepartmentByCode(String code) {
        return departmentRepository.findByCode(code);
    }

    public Department getDefaultDepartment(String code, Exception exception) {
        return Department.builder()
                .id(null)
                .name("Default Department")
                .description("Fake Department fallback")
                .code("FD001")
                .build();
    }
}
