package org.rrhh.employee.infrastructure.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.rrhh.employee.application.usecase.*;
import org.rrhh.employee.domain.document.Employee;
import org.rrhh.employee.infrastructure.controller.dto.EmployeeRequestDto;
import org.rrhh.employee.infrastructure.controller.dto.EmployeeResponseDto;
import org.rrhh.employee.infrastructure.controller.mapper.GenericMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeSaveUseCase employeeSave;
    private final EmployeeFindByIdUseCase employeeFindById;
    private final EmployeeFindAllUseCase employeeFindAll;
    private final EmployeeExistsByEmailUseCase employeeExistsByEmail;
    private final EmployeeDeleteUseCase employeeDelete;

    private final GenericMapper<EmployeeResponseDto, Employee, EmployeeRequestDto> employeeMapper;

    @PostMapping("/create")
    public ResponseEntity<EmployeeResponseDto> createEmployee(
            @RequestBody @Valid EmployeeRequestDto requestDto) {
        employeeExistsByEmail.existsByEmail(requestDto.email());
        Employee employeeDomain = employeeMapper.toDomain(requestDto);
        employeeDomain = employeeSave.createEmployee(employeeDomain);
        EmployeeResponseDto responseDto = employeeMapper.toDto(employeeDomain);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeResponseDto> getEmployeeByID(@PathVariable String employeeId) {
        Employee employeeDomain = employeeFindById.getEmployeeById(employeeId);
        EmployeeResponseDto responseDto = employeeMapper.toDto(employeeDomain);

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponseDto>> getEmployees() {
        List<EmployeeResponseDto> responseDtoList = employeeFindAll.getEmployees().stream()
                .map(employeeMapper::toDto)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(responseDtoList);
    }

    @DeleteMapping("{/employeeId}")
    public ResponseEntity<Void> deleteEmployeeById(@PathVariable String employeeId) {
        Employee employeeDomain = employeeFindById.getEmployeeById(employeeId);
        employeeDelete.deleteEmployee(employeeDomain);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
