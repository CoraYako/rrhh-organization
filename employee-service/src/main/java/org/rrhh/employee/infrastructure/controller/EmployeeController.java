package org.rrhh.employee.infrastructure.controller;

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
import org.rrhh.employee.domain.EmployeeConstants;
import org.rrhh.employee.domain.document.Employee;
import org.rrhh.employee.domain.exception.ErrorDetails;
import org.rrhh.employee.domain.usecase.EmployeeCreateUseCase;
import org.rrhh.employee.domain.usecase.EmployeeDeleteUseCase;
import org.rrhh.employee.domain.usecase.EmployeeGetAllUseCase;
import org.rrhh.employee.domain.usecase.EmployeeGetByIdUseCase;
import org.rrhh.employee.infrastructure.controller.dto.EmployeeBasicResponseDTO;
import org.rrhh.employee.infrastructure.controller.dto.EmployeeCompleteResponseDTO;
import org.rrhh.employee.infrastructure.controller.dto.EmployeeListDTO;
import org.rrhh.employee.infrastructure.controller.dto.EmployeeRequestDTO;
import org.rrhh.employee.infrastructure.controller.mapper.GenericMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    private final GenericMapper<EmployeeCompleteResponseDTO, EmployeeBasicResponseDTO, Employee, EmployeeRequestDTO>
            employeeMapper;

    @Operation(
            summary = "Employee Creation And Registration",
            description = "Creates an employee in the database."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Employee created",
                            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = EmployeeCompleteResponseDTO.class))}
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = """
                                    A 404 Not Found can occur due to:
                                    - Department not found
                                    - Organization not found
                                    
                                    Use the dropdown button to switch between the different examples.""",
                            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = {
                                    @ExampleObject(
                                            name = "Department Not Found",
                                            value = "{\"timestamp\": \"2023-09-03T10:58:51.3207771\","
                                                    + "\"message\": \"Department not found with code : 'AAA001'\","
                                                    + "\"path\": \"uri=/api/v1/departments/AAA001\","
                                                    + "\"errorCode\": \"RESOURCE_NOT_FOUND\""
                                                    + "}"
                                    ),
                                    @ExampleObject(
                                            name = "Organization Not Found",
                                            value = "{\"timestamp\": \"2023-09-03T10:58:51.3207771\","
                                                    + "\"message\": \"Organization not found with code : 'BBB002'\","
                                                    + "\"path\": \"uri=/api/v1/organizations/BBB002\","
                                                    + "\"errorCode\": \"RESOURCE_NOT_FOUND\""
                                                    + "}"
                                    )
                            },
                            schema = @Schema(implementation = ErrorDetails.class))}
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = """
                                    A 400 Bad Request can occur due to:
                                    - The required request body (payload) is missing
                                    - Email is already registered in the database
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
                                                    + "\"path\": \"uri=/api/v1/employees\","
                                                    + "\"errorCode\": \"INVALID_REQUIRED_PAYLOAD\""
                                                    + "}"
                                    ),
                                    @ExampleObject(
                                            name = "Email Error",
                                            description = "When the email is already registered in the database.",
                                            value = "{\"timestamp\": \"2023-09-03T10:58:51.3207771\","
                                                    + "\"message\": \"Employee already exist with email : 'test@email.com'\","
                                                    + "\"path\": \"uri=/api/v1/employees\","
                                                    + "\"errorCode\": \"DUPLICATED_RESOURCE\""
                                                    + "}"
                                    ),
                                    @ExampleObject(
                                            name = "Body Attribute Error",
                                            description = "If all body attributes are missing, " +
                                                    "it will be displayed as the example. " +
                                                    "Otherwise, only the missing attribute will be displayed.",
                                            value = "{\"firstName\": \"should not be empty\","
                                                    + "\"lastName\": \"should not be empty\","
                                                    + "\"organizationCode\": \"should not be empty\","
                                                    + "\"departmentCode\": \"should not be empty\","
                                                    +"\"email\": \"should not be empty\""
                                                    + "}"
                                    )
                            },
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
                            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = EmployeeCompleteResponseDTO.class))}
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Employee not found",
                            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = {@ExampleObject(
                                    value = "{\"timestamp\": \"2023-09-03T10:58:51.3207771\","
                                            + "\"message\": \"Employee not found with ID : '64ee5bd81d1dd84246498d44'\","
                                            + "\"path\": \"uri=/api/v1/employees/64ee5bd81d1dd84246498d44\","
                                            + "\"errorCode\": \"RESOURCE_NOT_FOUND\""
                                            + "}"
                            )},
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
                            description = "List all employees.If there is no employees, " +
                                    "an empty list is returned.",
                            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = EmployeeListDTO.class))}
                    )
            }
    )
    @GetMapping("/")
    public ResponseEntity<EmployeeListDTO> listEmployees() {
        List<EmployeeBasicResponseDTO> dtoList = employeeGetAll.getEmployees()
                .stream()
                .map(employeeMapper::toDTO)
                .toList();
        EmployeeListDTO responseDtoList = EmployeeListDTO.builder()
                .employees(dtoList)
                .build();

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
                            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = {@ExampleObject(
                                    value = "{\"timestamp\": \"2023-09-03T10:58:51.3207771\","
                                            + "\"message\": \"Employee not found with ID : '64ee5bd81d1dd84246498d44'\","
                                            + "\"path\": \"uri=/api/v1/employees/64ee5bd81d1dd84246498d44\","
                                            + "\"errorCode\": \"RESOURCE_NOT_FOUND\""
                                            + "}"
                            )},
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
