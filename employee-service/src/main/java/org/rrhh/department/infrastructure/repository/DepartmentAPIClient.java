package org.rrhh.department.infrastructure.repository;

import org.rrhh.department.domain.DepartmentConstants;
import org.rrhh.department.infrastructure.dto.DepartmentResponseDTO;
import org.rrhh.employee.infrastructure.config.FeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = DepartmentConstants.MICROSERVICE_EUREKA_CLIENT_ID, configuration = FeignClientConfiguration.class)
public interface DepartmentAPIClient {

    @GetMapping(value = DepartmentConstants.GET_BY_CODE_URI)
    ResponseEntity<DepartmentResponseDTO> getDepartmentByCode(@PathVariable String departmentCode);
}
