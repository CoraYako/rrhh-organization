package org.rrhh.organization.infrastructure.repository;

import org.rrhh.employee.infrastructure.config.FeignClientConfiguration;
import org.rrhh.organization.domain.OrganizationConstants;
import org.rrhh.organization.infrastructure.dto.OrganizationResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = OrganizationConstants.MICROSERVICE_EUREKA_CLIENT_ID, configuration = FeignClientConfiguration.class)
public interface OrganizationAPIClient {

    @GetMapping(value = OrganizationConstants.GET_BY_CODE_URI)
    ResponseEntity<OrganizationResponseDTO> getOrganizationByCode(@PathVariable String organizationCode);
}
