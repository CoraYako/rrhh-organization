package org.rrhh.department.infrastructure.controller;

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
import org.rrhh.department.domain.DepartmentConstants;
import org.rrhh.department.domain.document.Department;
import org.rrhh.department.domain.exception.ErrorDetails;
import org.rrhh.department.domain.usecase.DepartmentCreateUseCase;
import org.rrhh.department.domain.usecase.DepartmentGetAllUseCase;
import org.rrhh.department.domain.usecase.DepartmentGetByCodeUseCase;
import org.rrhh.department.infrastructure.controller.dto.DepartmentListDTO;
import org.rrhh.department.infrastructure.controller.dto.DepartmentRequestDTO;
import org.rrhh.department.infrastructure.controller.dto.DepartmentResponseDTO;
import org.rrhh.department.infrastructure.controller.mapper.GenericMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Department Controller",
        description = "Exposes REST APIs endpoints for Department-Service"
)
@RestController
@RequestMapping(value = DepartmentConstants.BASE_URI)
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentCreateUseCase departmentSave;
    private final DepartmentGetByCodeUseCase departmentFindByCode;
    private final DepartmentGetAllUseCase departmentFindAll;

    private final GenericMapper<DepartmentResponseDTO, Department, DepartmentRequestDTO> departmentMapper;

    @Operation(
            summary = "Department Creation And Registration",
            description = "Creates a department in the database"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Department created",
                            content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = DepartmentResponseDTO.class))}
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = """
                                    A 400 Bad Request can occur due to:
                                    - Payload body is missing
                                    - Duplicated department name or code
                                    - Null or empty body attribute
                                    
                                    Use the dropdown button to switch between the different examples.""",
                            content = {@Content(mediaType = "application/json",
                            examples = {
                                    @ExampleObject(
                                            name = "Payload Body Missing",
                                            description = "It occurs when tries to create a resource " +
                                                    "without the body (payload).",
                                            value = "{\"timestamp\": \"2023-09-03T10:58:51.3207771\","
                                                    + "\"message\": \"Required request body is missing\","
                                                    + "\"path\": \"uri=/api/v1/departments\","
                                                    + "\"errorCode\": \"INVALID_REQUIRED_PAYLOAD\""
                                                    + "}"
                                    ),
                                    @ExampleObject(
                                            name = "Name/Code Error",
                                            description = "When a department with the same name or code (or both) " +
                                                    "already exists in the database.",
                                            value = "{\"timestamp\": \"2023-09-03T10:58:51.3207771\","
                                                    + "\"message\": \"Department already exist with code : 'IT001'\","
                                                    + "\"path\": \"uri=/api/v1/departments\","
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
    public ResponseEntity<DepartmentResponseDTO> createDepartment(@RequestBody @Valid DepartmentRequestDTO requestDTO) {
        Department departmentDomain = departmentMapper.toDomain(requestDTO);
        departmentDomain = departmentSave.createDepartment(departmentDomain);
        DepartmentResponseDTO responseDTO = departmentMapper.toDTO(departmentDomain);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @Operation(
            summary = "Get Department",
            description = "Gets a single department by its code"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Found the department",
                            content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = DepartmentResponseDTO.class))}
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Department not found",
                            content = {@Content(mediaType = "application/json",
                            examples = {@ExampleObject(
                                    value = "{\"timestamp\": \"2023-09-03T10:58:51.3207771\","
                                            + "\"message\": \"Department not found with code : 'AAA001'\","
                                            + "\"path\": \"uri=/api/v1/departments/AAA001\","
                                            + "\"errorCode\": \"RESOURCE_NOT_FOUND\""
                                            + "}"
                            )},
                            schema = @Schema(implementation = ErrorDetails.class))}
                    )
            }
    )
    @Parameter(
            name = "departmentCode",
            description = "Department Code",
            example = "IT001"
    )
    @GetMapping(value = "/{departmentCode}")
    public ResponseEntity<DepartmentResponseDTO> getDepartmentByCode(@PathVariable String departmentCode) {
        Department departmentDomain = departmentFindByCode.getDepartmentByCode(departmentCode);
        DepartmentResponseDTO responseDTO = departmentMapper.toDTO(departmentDomain);

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @Operation(
            summary = "List Departments",
            description = "Gets a list of all departments"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "List all departments. If there is no departments, " +
                                    "an empty list is returned.",
                            content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = DepartmentListDTO.class))}
                    )
            }
    )
    @GetMapping("/")
    public ResponseEntity<DepartmentListDTO> listDepartments() {
        List<DepartmentResponseDTO> dtoList = departmentFindAll.getDepartments()
                .stream()
                .map(departmentMapper::toDTO)
                .toList();
        DepartmentListDTO responseDTOList = DepartmentListDTO.builder()
                .departments(dtoList)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(responseDTOList);
    }
}
