package org.rrhh.employee.infrastructure.controller.mapper;

import jakarta.validation.constraints.NotNull;

public interface GenericMapper<D, B, E, R> {

    E toDomain(@NotNull R dto);

    D toCompleteDTO(@NotNull E document);

    B toDTO(@NotNull E document);
}
