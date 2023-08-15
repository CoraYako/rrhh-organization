package org.rrhh.employee.infrastructure.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.rrhh.employee.application.usecase.EmployeeDeleteUseCase;
import org.rrhh.employee.application.usecase.EmployeeFindAllUseCase;
import org.rrhh.employee.application.usecase.EmployeeFindByIdUseCase;
import org.rrhh.employee.application.usecase.EmployeeSaveUseCase;
import org.rrhh.employee.domain.EmployeeConstants;
import org.rrhh.employee.domain.document.Employee;
import org.rrhh.employee.infrastructure.controller.dto.EmployeeBasicResponseDTO;
import org.rrhh.employee.infrastructure.controller.dto.EmployeeCompleteResponseDTO;
import org.rrhh.employee.infrastructure.controller.dto.EmployeeRequestDTO;
import org.rrhh.employee.infrastructure.controller.mapper.GenericMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = EmployeeConstants.BASE_URI)
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeSaveUseCase employeeSave;
    private final EmployeeFindByIdUseCase employeeFindById;
    private final EmployeeFindAllUseCase employeeFindAll;
    private final EmployeeDeleteUseCase employeeDelete;

    private final GenericMapper<EmployeeCompleteResponseDTO, EmployeeBasicResponseDTO, Employee, EmployeeRequestDTO> employeeMapper;

    @PostMapping(value = "/create")
    public ResponseEntity<EmployeeCompleteResponseDTO> createEmployee(@RequestBody @Valid EmployeeRequestDTO requestDTO) {
        Employee employeeDomain = employeeMapper.toDomain(requestDTO);
        employeeDomain = employeeSave.createEmployee(employeeDomain);
        EmployeeCompleteResponseDTO responseDTO = employeeMapper.toCompleteDTO(employeeDomain);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping(value = "/{employeeId}")
    public ResponseEntity<EmployeeCompleteResponseDTO> getEmployeeByID(@PathVariable String employeeId) {
        Employee employeeDomain = employeeFindById.getEmployeeById(employeeId);
        EmployeeCompleteResponseDTO responseDTO = employeeMapper.toCompleteDTO(employeeDomain);

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeBasicResponseDTO>> getEmployees() {
        List<EmployeeBasicResponseDTO> responseDtoList = employeeFindAll.getEmployees()
                .stream()
                .map(employeeMapper::toDTO)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(responseDtoList);
    }

    @DeleteMapping(value = "/{employeeId}")
    public ResponseEntity<Void> deleteEmployeeById(@PathVariable String employeeId) {
        Employee employeeDomain = employeeFindById.getEmployeeById(employeeId);
        employeeDelete.deleteEmployee(employeeDomain);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
