package org.rrhh.employee.infrastructure.controller.mapper;

import org.rrhh.employee.domain.document.Employee;
import org.rrhh.employee.infrastructure.controller.dto.EmployeeRequestDTO;
import org.rrhh.employee.infrastructure.controller.dto.EmployeeResponseDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
public class EmployeeControllerMapper implements GenericMapper<EmployeeResponseDTO, Employee, EmployeeRequestDTO> {

    @Override
    public Employee toDomain(EmployeeRequestDTO dto) {
        return Employee.builder()
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .email(dto.email())
                .build();
    }

    @Override
    public EmployeeResponseDTO toDTO(Employee document) {
        return EmployeeResponseDTO.builder()
                .id(document.getId().getValue())
                .firstName(document.getFirstName().getValue())
                .lastName(document.getLastName().getValue())
                .email(document.getEmail().getValue())
                .build();
    }
}
