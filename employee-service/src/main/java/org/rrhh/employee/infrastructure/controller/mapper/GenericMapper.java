package org.rrhh.employee.infrastructure.controller.mapper;

public interface GenericMapper <D, E, R>{

    E toDomain(R dto);

    D toDto(E document);
}
