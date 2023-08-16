package org.rrhh.department.infrastructure.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.rrhh.department.application.usecase.DepartmentCreateUseCase;
import org.rrhh.department.application.usecase.DepartmentGetAllUseCase;
import org.rrhh.department.application.usecase.DepartmentGetByCodeUseCase;
import org.rrhh.department.domain.DepartmentConstants;
import org.rrhh.department.domain.document.Department;
import org.rrhh.department.infrastructure.controller.dto.DepartmentRequestDTO;
import org.rrhh.department.infrastructure.controller.dto.DepartmentResponseDTO;
import org.rrhh.department.infrastructure.controller.mapper.GenericMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = DepartmentConstants.BASE_URI)
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentCreateUseCase departmentSave;
    private final DepartmentGetByCodeUseCase departmentFindByCode;
    private final DepartmentGetAllUseCase departmentFindAll;

    private final GenericMapper<DepartmentResponseDTO, Department, DepartmentRequestDTO> departmentMapper;

    @PostMapping(value = "/create")
    public ResponseEntity<DepartmentResponseDTO> createDepartment(@RequestBody @Valid DepartmentRequestDTO requestDTO) {
        Department departmentDomain = departmentMapper.toDomain(requestDTO);
        departmentDomain = departmentSave.createDepartment(departmentDomain);
        DepartmentResponseDTO responseDTO = departmentMapper.toDTO(departmentDomain);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping(value = "/{departmentCode}")
    public ResponseEntity<DepartmentResponseDTO> getDepartmentByCode(@PathVariable String departmentCode) {
        Department departmentDomain = departmentFindByCode.getDepartmentByCode(departmentCode);
        DepartmentResponseDTO responseDTO = departmentMapper.toDTO(departmentDomain);

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentResponseDTO>> listDepartments() {
        List<DepartmentResponseDTO> responseDTOList = departmentFindAll.getDepartments()
                .stream()
                .map(departmentMapper::toDTO)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(responseDTOList);
    }
}
