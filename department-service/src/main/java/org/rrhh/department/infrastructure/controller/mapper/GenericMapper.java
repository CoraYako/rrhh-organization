package org.rrhh.department.infrastructure.controller.mapper;

import jakarta.validation.constraints.NotNull;

public interface GenericMapper<D, E, R> {

    E toDomain(@NotNull R dto);

    D toDTO(@NotNull E document);
}
