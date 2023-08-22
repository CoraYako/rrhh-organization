package org.rrhh.department.application.implementation;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.rrhh.department.application.usecase.DepartmentGetByCodeUseCase;
import org.rrhh.department.domain.DepartmentConstants;
import org.rrhh.department.domain.document.Department;
import org.rrhh.department.domain.repository.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DepartmentGetByCodeImpl implements DepartmentGetByCodeUseCase {

    private final Logger LOGGER = LoggerFactory.getLogger(DepartmentGetByCodeImpl.class);
    private final DepartmentRepository departmentRepository;

    public DepartmentGetByCodeImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    //@Retry(name = "${spring.application.name}", fallbackMethod = DepartmentConstants.METHOD_NAME)
    @CircuitBreaker(name = "${spring.application.name}", fallbackMethod = DepartmentConstants.METHOD_NAME)
    public Department getDepartmentByCode(String code) {
        LOGGER.info("#####----- inside getDepartmentByCode() method -----#####");

        return departmentRepository.findByCode(code);
    }

    public Department getDefaultDepartment(String code, Exception exception) {
        LOGGER.error("#####----- inside getDefaultDepartment() method -----#####");
        LOGGER.error(exception.getLocalizedMessage());

        return Department.builder()
                .id(null)
                .name("Default Department")
                .description("Fake Department fallback")
                .code(code)
                .build();
    }
}
