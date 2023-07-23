package org.rrhh.employee.infrastructure.controller.mapper;

import org.rrhh.employee.domain.document.Employee;
import org.rrhh.employee.infrastructure.controller.dto.EmployeeRequestDto;
import org.rrhh.employee.infrastructure.controller.dto.EmployeeResponseDto;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper implements GenericMapper<EmployeeResponseDto, Employee, EmployeeRequestDto> {

    @Override
    public Employee toDomain(EmployeeRequestDto dto) {
        return Employee.builder()
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .email(dto.email())
                .build();
    }

    @Override
    public EmployeeResponseDto toDto(Employee document) {
        return EmployeeResponseDto.builder()
                .id(document.getId().getValue())
                .firstName(document.getFirstName().getValue())
                .lastName(document.getLastName().getValue())
                .email(document.getEmail().getValue())
                .build();
    }
}
