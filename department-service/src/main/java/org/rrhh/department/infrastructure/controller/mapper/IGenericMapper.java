package org.rrhh.department.infrastructure.controller.mapper;

public interface IGenericMapper<D, E, R> {

    E toDomain(R dto);

    D toDto(E document);
}
