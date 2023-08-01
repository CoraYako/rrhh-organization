package org.rrhh.department.infrastructure.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.rrhh.department.application.usecase.DepartmentDeleteUseCase;
import org.rrhh.department.application.usecase.DepartmentFindAllUseCase;
import org.rrhh.department.application.usecase.DepartmentFindByIdUseCase;
import org.rrhh.department.application.usecase.DepartmentSaveUseCase;
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

    private final DepartmentSaveUseCase departmentSave;
    private final DepartmentFindByIdUseCase departmentFindById;
    private final DepartmentFindAllUseCase departmentFindAll;
    private final DepartmentDeleteUseCase departmentDelete;

    private final GenericMapper<DepartmentResponseDTO, Department, DepartmentRequestDTO> departmentMapper;

    @PostMapping(value = "/create")
    public ResponseEntity<DepartmentResponseDTO> createDepartment(@RequestBody @Valid DepartmentRequestDTO requestDTO) {
        Department departmentDomain = departmentMapper.toDomain(requestDTO);
        departmentDomain = departmentSave.save(departmentDomain);
        DepartmentResponseDTO responseDTO = departmentMapper.toDTO(departmentDomain);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping(value = "/{departmentId}")
    public ResponseEntity<DepartmentResponseDTO> getDepartmentById(@PathVariable String departmentId) {
        Department departmentDomain = departmentFindById.getDepartmentById(departmentId);
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

    @DeleteMapping(value = "/{departmentId}")
    public ResponseEntity<Void> deleteDepartmentById(@PathVariable String departmentId) {
        Department departmentDomain = departmentFindById.getDepartmentById(departmentId);
        departmentDelete.deleteDepartment(departmentDomain);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
