package org.rrhh.employee.infrastructure.controller.mapper;

import lombok.RequiredArgsConstructor;
import org.rrhh.department.infrastructure.mapper.DepartmentMapper;
import org.rrhh.employee.domain.document.Employee;
import org.rrhh.employee.infrastructure.controller.dto.EmployeeBasicResponseDTO;
import org.rrhh.employee.infrastructure.controller.dto.EmployeeCompleteResponseDTO;
import org.rrhh.employee.infrastructure.controller.dto.EmployeeRequestDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
@RequiredArgsConstructor
public class EmployeeControllerMapper implements
        GenericMapper<EmployeeCompleteResponseDTO, EmployeeBasicResponseDTO, Employee, EmployeeRequestDTO> {

    private final DepartmentMapper departmentMapper;

    @Override
    public Employee toDomain(EmployeeRequestDTO dto) {
        return Employee.builder()
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .email(dto.email())
                .departmentCode(dto.departmentCode())
                .build();
    }

    @Override
    public EmployeeCompleteResponseDTO toCompleteDTO(Employee document) {
        return EmployeeCompleteResponseDTO.builder()
                .id(document.getId().getValue())
                .firstName(document.getFirstName().getValue())
                .lastName(document.getLastName().getValue())
                .email(document.getEmail().getValue())
                .departmentCode(document.getDepartmentCode().getValue())
                .associatedDepartment(departmentMapper.toDTO(document.getDepartment().getValue()))
                .build();
    }

    @Override
    public EmployeeBasicResponseDTO toDTO(Employee document) {
        return EmployeeBasicResponseDTO.builder()
                .id(document.getId().getValue())
                .firstName(document.getFirstName().getValue())
                .lastName(document.getLastName().getValue())
                .email(document.getEmail().getValue())
                .departmentCode(document.getDepartmentCode().getValue())
                .build();
    }
}
