package org.rrhh.department.infrastructure.repository;

import org.rrhh.department.domain.DepartmentConstants;
import org.rrhh.department.infrastructure.dto.DepartmentResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = DepartmentConstants.MICROSERVICE_EUREKA_CLIENT_ID)
public interface APIClient {

    @GetMapping(value = DepartmentConstants.GET_BY_CODE_URI)
    DepartmentResponseDTO getDepartmentByCode(@PathVariable String departmentCode);
}
