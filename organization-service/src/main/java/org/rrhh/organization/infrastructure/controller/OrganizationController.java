package org.rrhh.organization.infrastructure.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.rrhh.organization.domain.OrganizationConstants;
import org.rrhh.organization.domain.document.Organization;
import org.rrhh.organization.domain.exception.ErrorDetails;
import org.rrhh.organization.domain.usecase.OrganizationCreateUseCase;
import org.rrhh.organization.domain.usecase.OrganizationGetAllUseCase;
import org.rrhh.organization.domain.usecase.OrganizationGetByCodeUseCase;
import org.rrhh.organization.infrastructure.controller.dto.OrganizationListDTO;
import org.rrhh.organization.infrastructure.controller.dto.OrganizationRequestDTO;
import org.rrhh.organization.infrastructure.controller.dto.OrganizationResponseDTO;
import org.rrhh.organization.infrastructure.controller.mapper.GenericMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Organization Controller",
        description = "Exposes REST APIs endpoints for Organization-Service"
)
@RestController
@RequestMapping(value = OrganizationConstants.BASE_URI)
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationCreateUseCase organizationCreate;
    private final OrganizationGetByCodeUseCase organizationGetByCode;
    private final OrganizationGetAllUseCase organizationGetAll;

    private final GenericMapper<OrganizationResponseDTO, Organization, OrganizationRequestDTO> organizationMapper;

    @Operation(
            summary = "Organization Creation And Registration",
            description = "Creates an organization in the database"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Organization created",
                            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = OrganizationResponseDTO.class))}
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = """
                                    A 400 Bad Request can occur due to:
                                    - Payload body is missing
                                    - Duplicated organization name or code
                                    - Null or empty body attribute
                                    
                                    Use the dropdown button to switch between the different examples.""",
                            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = {
                                    @ExampleObject(
                                            name = "Payload Body Missing",
                                            description = "It occurs when tries to create a resource " +
                                                    "without the body (payload).",
                                            value = "{\"timestamp\": \"2023-09-03T10:58:51.3207771\","
                                                    + "\"message\": \"Required request body is missing\","
                                                    + "\"path\": \"uri=/api/v1/organizations\","
                                                    + "\"errorCode\": \"INVALID_REQUIRED_PAYLOAD\""
                                                    + "}"
                                    ),
                                    @ExampleObject(
                                            name = "Name/Code Error",
                                            description = "When an organization with the same name or code (or both) " +
                                                    "already exists in the database.",
                                            value = "{\"timestamp\": \"2023-09-03T10:58:51.3207771\","
                                                    + "\"message\": \"Organization already exist with code : 'SF001'\","
                                                    + "\"path\": \"uri=/api/v1/organizations\","
                                                    + "\"errorCode\": \"DUPLICATED_RESOURCE\""
                                                    + "}"
                                    ),
                                    @ExampleObject(
                                            name = "Body Attribute Error",
                                            description = "If all body attributes are missing, " +
                                                    "it will be displayed as the example. " +
                                                    "Otherwise, only the missing attribute will be displayed.",
                                            value = "{\"name\": \"should not be empty\","
                                                    + "\"description\": \"should not be empty\","
                                                    + "\"code\": \"should not be empty\""
                                                    + "}"
                                    )
                            },
                            schema = @Schema(implementation = ErrorDetails.class))}
                    )
            }
    )
    @PostMapping
    public ResponseEntity<OrganizationResponseDTO> createOrganization(@RequestBody @Valid OrganizationRequestDTO requestDTO) {
        Organization organizationDomain = organizationMapper.toDomain(requestDTO);
        organizationDomain = organizationCreate.createOrganization(organizationDomain);
        OrganizationResponseDTO responseDTO = organizationMapper.toDTO(organizationDomain);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @Operation(
            summary = "Get Organization",
            description = "Gets a single organization by its code"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Found the organization",
                            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = OrganizationResponseDTO.class))}
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Organization not found",
                            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = {@ExampleObject(
                                    value = "{\"timestamp\": \"2023-09-03T10:58:51.3207771\","
                                            + "\"message\": \"Organization not found with code : 'BBB002'\","
                                            + "\"path\": \"uri=/api/v1/organizations/BBB002\","
                                            + "\"errorCode\": \"RESOURCE_NOT_FOUND\""
                                            + "}"
                            )},
                            schema = @Schema(implementation = ErrorDetails.class))}
                    )
            }
    )
    @Parameter(
            name = "organizationCode",
            description = "Organization Code",
            example = "SF001"
    )
    @GetMapping(value = "/{organizationCode}")
    public ResponseEntity<OrganizationResponseDTO> getOrganizationByCode(@PathVariable String organizationCode) {
        Organization organizationDomain = organizationGetByCode.getOrganizationByCode(organizationCode);
        OrganizationResponseDTO responseDTO = organizationMapper.toDTO(organizationDomain);

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @Operation(
            summary = "List Organizations",
            description = "Gets a list of all organizations"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "List all organizations.If there is no organizations, " +
                                    "an empty list is returned.",
                            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = OrganizationListDTO.class))}
                    )
            }
    )
    @GetMapping("/")
    public ResponseEntity<OrganizationListDTO> listOrganizations() {
        List<OrganizationResponseDTO> dtoList = organizationGetAll.getOrganizations()
                .stream()
                .map(organizationMapper::toDTO)
                .toList();
        OrganizationListDTO responseDTOList = OrganizationListDTO.builder()
                .organizations(dtoList)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(responseDTOList);
    }
}
