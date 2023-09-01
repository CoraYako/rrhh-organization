package org.rrhh.employee.infrastructure.controller;

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
import org.rrhh.employee.application.usecase.EmployeeCreateUseCase;
import org.rrhh.employee.application.usecase.EmployeeDeleteUseCase;
import org.rrhh.employee.application.usecase.EmployeeGetAllUseCase;
import org.rrhh.employee.application.usecase.EmployeeGetByIdUseCase;
import org.rrhh.employee.domain.EmployeeConstants;
import org.rrhh.employee.domain.document.Employee;
import org.rrhh.employee.domain.exception.ErrorDetails;
import org.rrhh.employee.infrastructure.controller.dto.EmployeeBasicResponseDTO;
import org.rrhh.employee.infrastructure.controller.dto.EmployeeCompleteResponseDTO;
import org.rrhh.employee.infrastructure.controller.dto.EmployeeRequestDTO;
import org.rrhh.employee.infrastructure.controller.mapper.GenericMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Employee Controller",
        description = "Exposes REST APIs endpoints for Employee-Service"
)
@RestController
@RequestMapping(value = EmployeeConstants.BASE_URI)
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeCreateUseCase employeeCreate;
    private final EmployeeGetByIdUseCase employeeGetById;
    private final EmployeeGetAllUseCase employeeGetAll;
    private final EmployeeDeleteUseCase employeeDelete;

    private final GenericMapper<EmployeeCompleteResponseDTO, EmployeeBasicResponseDTO, Employee, EmployeeRequestDTO> employeeMapper;

    @Operation(
            summary = "Employee Creation And Registration",
            description = "Creates an employee in the database"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Employee created",
                            content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeBasicResponseDTO.class))}
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
    public ResponseEntity<EmployeeCompleteResponseDTO> createEmployee(@RequestBody @Valid EmployeeRequestDTO requestDTO) {
        Employee employeeDomain = employeeMapper.toDomain(requestDTO);
        employeeDomain = employeeCreate.createEmployee(employeeDomain);
        EmployeeCompleteResponseDTO responseDTO = employeeMapper.toCompleteDTO(employeeDomain);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @Operation(
            summary = "Get Employee",
            description = "Gets an employee by its ID, this includes " +
                    "associated Department and Organization information"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Found the employee",
                            content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeCompleteResponseDTO.class))}
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Employee not found",
                            content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetails.class))}
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid ID supplied",
                            content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetails.class))}
                    )
            }
    )
    @Parameter(
            name = "employeeId",
            description = "Employee ID",
            example = "64ee5bd81d1dd84246498d44"
    )
    @GetMapping(value = "/{employeeId}")
    public ResponseEntity<EmployeeCompleteResponseDTO> getEmployeeByID(@PathVariable String employeeId) {
        Employee employeeDomain = employeeGetById.getEmployeeById(employeeId);
        EmployeeCompleteResponseDTO responseDTO = employeeMapper.toCompleteDTO(employeeDomain);

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @Operation(
            summary = "List Employees",
            description = "Gets a list of all employees with basic information per employee"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "List all employees",
                            content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(
                            schema = @Schema(implementation = EmployeeBasicResponseDTO.class)))}
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No employees found",
                            content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetails.class))}
                    )
            }
    )
    @GetMapping("/")
    public ResponseEntity<List<EmployeeBasicResponseDTO>> listEmployees() {
        List<EmployeeBasicResponseDTO> responseDtoList = employeeGetAll.getEmployees()
                .stream()
                .map(employeeMapper::toDTO)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(responseDtoList);
    }

    @Operation(
            summary = "Delete Employee",
            description = "Eliminates an employee from the database. This can be done by supplying the ID"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Employee deleted with no content to display"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Employee not found",
                            content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetails.class))}
                    )
            }
    )
    @Parameter(
            name = "employeeId",
            description = "Employee ID",
            example = "64ee5bd81d1dd84246498d44"
    )
    @DeleteMapping(value = "/{employeeId}")
    public ResponseEntity<Void> deleteEmployeeById(@PathVariable String employeeId) {
        Employee employeeDomain = employeeGetById.getEmployeeById(employeeId);
        employeeDelete.deleteEmployee(employeeDomain);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
