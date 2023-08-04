package org.rrhh.department.infrastructure.repository;

import org.rrhh.department.infrastructure.dto.DepartmentResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://localhost:9091", name = "department-service")
public interface DepartmentFeign {

    @GetMapping(value = "/api/v1/departments/code/{departmentCode}")
    DepartmentResponseDTO getDepartmentByCode(@PathVariable String departmentCode);
}
