package org.rrhh.organization.infrastructure.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.rrhh.organization.application.usecase.OrganizationFindAllUseCase;
import org.rrhh.organization.application.usecase.OrganizationFindByCodeUseCase;
import org.rrhh.organization.application.usecase.OrganizationSaveUseCase;
import org.rrhh.organization.domain.OrganizationConstants;
import org.rrhh.organization.domain.document.Organization;
import org.rrhh.organization.infrastructure.controller.dto.OrganizationRequestDTO;
import org.rrhh.organization.infrastructure.controller.dto.OrganizationResponseDTO;
import org.rrhh.organization.infrastructure.controller.mapper.GenericMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = OrganizationConstants.BASE_URI)
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationSaveUseCase organizationSave;
    private final OrganizationFindByCodeUseCase organizationFindByCode;
    private final OrganizationFindAllUseCase organizationFindAll;

    private final GenericMapper<OrganizationResponseDTO, Organization, OrganizationRequestDTO> organizationMapper;

    @PostMapping(value = "/create")
    public ResponseEntity<OrganizationResponseDTO> createOrganization(@RequestBody @Valid OrganizationRequestDTO requestDTO) {
        Organization organizationDomain = organizationMapper.toDomain(requestDTO);
        organizationDomain = organizationSave.createOrganization(organizationDomain);
        OrganizationResponseDTO responseDTO = organizationMapper.toDTO(organizationDomain);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping(value = "/{organizationCode}")
    public ResponseEntity<OrganizationResponseDTO> getOrganizationByCode(@PathVariable String organizationCode) {
        Organization organizationDomain = organizationFindByCode.getOrganizationByCode(organizationCode);
        OrganizationResponseDTO responseDTO = organizationMapper.toDTO(organizationDomain);

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<OrganizationResponseDTO>> listOrganizations() {
        List<OrganizationResponseDTO> responseDTOList = organizationFindAll.getAllOrganizations()
                .stream()
                .map(organizationMapper::toDTO)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(responseDTOList);
    }
}
