package org.rrhh.organization.infrastructure.repository;

import org.rrhh.organization.domain.OrganizationConstants;
import org.rrhh.organization.infrastructure.dto.OrganizationResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = OrganizationConstants.MICROSERVICE_EUREKA_CLIENT_ID)
public interface OrganizationAPIClient {

    @GetMapping(value = OrganizationConstants.GET_BY_CODE_URI)
    OrganizationResponseDTO getOrganizationByCode(@PathVariable String organizationCode);
}
