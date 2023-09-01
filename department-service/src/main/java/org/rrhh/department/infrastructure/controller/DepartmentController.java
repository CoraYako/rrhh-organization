package org.rrhh.department.infrastructure.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.rrhh.department.application.usecase.DepartmentCreateUseCase;
import org.rrhh.department.application.usecase.DepartmentGetAllUseCase;
import org.rrhh.department.application.usecase.DepartmentGetByCodeUseCase;
import org.rrhh.department.domain.DepartmentConstants;
import org.rrhh.department.domain.document.Department;
import org.rrhh.department.domain.exception.ErrorDetails;
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
                            description = "Invalid or missing body content",
                            content = {@Content(mediaType = "application/json",
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
                            schema = @Schema(implementation = ErrorDetails.class))}
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid code supplied",
                            content = {@Content(mediaType = "application/json",
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
                            description = "List all departments",
                            content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(
                            schema = @Schema(implementation = DepartmentResponseDTO.class)))}
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No departments found",
                            content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetails.class))}
                    )
            }
    )
    @GetMapping("/")
    public ResponseEntity<List<DepartmentResponseDTO>> listDepartments() {
        List<DepartmentResponseDTO> responseDTOList = departmentFindAll.getDepartments()
                .stream()
                .map(departmentMapper::toDTO)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(responseDTOList);
    }
}
