package org.rrhh.department.infrastructure.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.rrhh.department.application.usecase.*;
import org.rrhh.department.domain.document.Department;
import org.rrhh.department.infrastructure.controller.dto.DepartmentRequestDto;
import org.rrhh.department.infrastructure.controller.dto.DepartmentResponseDto;
import org.rrhh.department.infrastructure.controller.mapper.IGenericMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentSaveUseCase departmentSave;
    private final DepartmentFindByIdUseCase departmentFindById;
    private final DepartmentFindAllUseCase departmentFindAll;
    private final DepartmentDeleteUseCase departmentDelete;
    private final DepartmentFindByNameUseCase departmentFindByName;
    private final DepartmentFindByCodeUseCase departmentFindByCode;

    private final IGenericMapper<DepartmentResponseDto, Department, DepartmentRequestDto> departmentMapper;

    @PostMapping("/create")
    public ResponseEntity<DepartmentResponseDto> createDepartment
            (@RequestBody @Valid DepartmentRequestDto departmentRequestDto) {
        Department department = departmentMapper.toDomain(departmentRequestDto);
        DepartmentResponseDto departmentResponseDto = departmentMapper.toDto(departmentSave.save(department));
        return ResponseEntity.status(HttpStatus.CREATED).body(departmentResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponseDto> getDepartmentById(@PathVariable String id) {
        DepartmentResponseDto departmentResponseDto = departmentMapper.toDto(departmentFindById.getDepartmentById(id));
        return ResponseEntity.status(HttpStatus.OK).body(departmentResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentResponseDto>> listDepartments() {
        List<DepartmentResponseDto> departmentResponseDtoList = departmentFindAll.getDepartments()
                .stream()
                .map(departmentMapper::toDto)
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(departmentResponseDtoList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartmentById(@PathVariable String id) {
        Department departmentFound = departmentFindById.getDepartmentById(id);
        departmentDelete.deleteDepartment(departmentFound);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<DepartmentResponseDto> getDepartmentByName(@PathVariable String name) {
        DepartmentResponseDto departmentResponseDto = departmentMapper
                .toDto(departmentFindByName.getDepartmentByName(name));
        return ResponseEntity.status(HttpStatus.OK).body(departmentResponseDto);
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<DepartmentResponseDto> getDepartmentByCode(@PathVariable String code) {
        DepartmentResponseDto departmentResponseDto = departmentMapper
                .toDto(departmentFindByCode.getDepartmentByCode(code));
        return ResponseEntity.status(HttpStatus.OK).body(departmentResponseDto);
    }
}
