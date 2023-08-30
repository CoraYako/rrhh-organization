package org.rrhh.department.application.implementation;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.rrhh.department.application.usecase.DepartmentGetByCodeUseCase;
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

    /*
    To use the Retry pattern, uncomment the @Retry that allows to call the external microservice
    a certain numbers of times before it ends up in the fallback method.

    @Retry(name = "${spring.application.name}", fallbackMethod = DepartmentConstants.METHOD_NAME)
    */
    @Override
    @CircuitBreaker(name = "${spring.application.name}")
    public Department getDepartmentByCode(String code) {
        LOGGER.info("#####----- inside getDepartmentByCode() method -----#####");

        return departmentRepository.findByCode(code);
    }

    /*
    To use the below method, add the following code inside the parentheses of @CircuitBreaker or @Retry annotation.
    Use a comma to separate from other specifications:
    fallbackMethod = DepartmentConstants.METHOD_NAME
    */
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
