package org.rrhh.employee.infrastructure.controller.mapper;

import lombok.RequiredArgsConstructor;
import org.rrhh.department.infrastructure.mapper.DepartmentMapper;
import org.rrhh.employee.domain.document.Employee;
import org.rrhh.employee.infrastructure.controller.dto.EmployeeBasicResponseDTO;
import org.rrhh.employee.infrastructure.controller.dto.EmployeeCompleteResponseDTO;
import org.rrhh.employee.infrastructure.controller.dto.EmployeeRequestDTO;
import org.rrhh.organization.infrastructure.mapper.OrganizationMapper;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
@RequiredArgsConstructor
public class EmployeeControllerMapperImpl implements
        GenericMapper<EmployeeCompleteResponseDTO, EmployeeBasicResponseDTO, Employee, EmployeeRequestDTO> {

    private final DepartmentMapper departmentMapper;
    private final OrganizationMapper organizationMapper;

    @Override
    public Employee toDomain(EmployeeRequestDTO dto) {
        return Employee.builder()
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .email(dto.email())
                .departmentCode(dto.departmentCode())
                .organizationCode(dto.organizationCode())
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
                .organizationCode(document.getOrganizationCode().getValue())
                .associatedDepartment(departmentMapper.toDTO(document.getDepartment().getValue()))
                .associatedOrganization(organizationMapper.toDTO(document.getOrganization().getValue()))
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
                .organizationCode(document.getOrganizationCode().getValue())
                .build();
    }
}
