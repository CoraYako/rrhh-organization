package org.rrhh.department.application.implementation;

import io.github.resilience4j.retry.annotation.Retry;
import org.rrhh.department.application.usecase.DepartmentFindByCodeUseCase;
import org.rrhh.department.domain.document.Department;
import org.rrhh.department.domain.repository.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DepartmentFindByCode implements DepartmentFindByCodeUseCase {

    private final DepartmentRepository departmentRepository;
    private final Logger logger = LoggerFactory.getLogger(DepartmentFindByCode.class);

    public DepartmentFindByCode(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    @Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    //@CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    public Department getDepartmentByCode(String code) {
        logger.info("#####----- inside getDepartmentByCode() method -----#####");
        return departmentRepository.findByCode(code);
    }

    public Department getDefaultDepartment(String code, Exception exception) {
        logger.info("#####----- inside getDefaultDepartment() method -----#####");
        logger.info(exception.getLocalizedMessage());
        return Department.builder()
                .id(null)
                .name("Default Department")
                .description("Fake Department fallback")
                .code("FD001")
                .build();
    }
}
